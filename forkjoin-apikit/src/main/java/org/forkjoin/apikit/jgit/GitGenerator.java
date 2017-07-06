package org.forkjoin.apikit.jgit;

import com.google.common.collect.Iterables;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.RmCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.forkjoin.apikit.AbstractFileGenerator;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

/**
 * @author zuoge85@gmail.com on 2017/6/22.
 */
public class GitGenerator implements Generator {
    private static final Logger log = LoggerFactory.getLogger(GitGenerator.class);

    private AbstractFileGenerator generator;
    private String gitUser;
    private String getPassword;

    /**
     * 对应git  user.name
     */
    private String gitName;
    /**
     * 对应git  user.email
     */
    private String gitEmail;

    private String gitUrl;
    private String gitBranch = "master";
    private String srcUri;
    private List<String> deleteUris;
    private String commitTemplate = "更新SDK版本:%s";


    @Override
    public void generate(Context context) throws Exception {
        Path tempDir = Files.createTempDirectory("apikit-git");
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(gitUser, getPassword);
        log.info("开始 git clone");
        try (Git git = Git.cloneRepository()
                .setURI(gitUrl)
                .setDirectory(tempDir.toFile())
                .setBranch(gitBranch)
                .setCredentialsProvider(cp)
                .call()) {

            log.info("git clone 成功");
            log.info("git临时目录:{}", tempDir.toAbsolutePath());

            File src = new File(tempDir.toFile(), srcUri);
            generator.setOutPath(src.getAbsolutePath());

            if (deleteUris != null && !deleteUris.isEmpty()) {
                for (String deleteUri : deleteUris) {
                    File delFile = new File(src, deleteUri);
                    log.info("清理目录:{}", delFile.getAbsolutePath());
                    FileUtils.deleteDirectory(delFile);
                }
            }

            log.info("开始执行生成器");
            generator.generate(context);

            DirCache dirCache = git.add().addFilepattern(".").call();
            log.info("添加文件: getEntryCount: {},dirCache:{}", dirCache.getEntryCount(), dirCache);


            Set<String> missing = git.status().call().getMissing();

            if (!missing.isEmpty()) {
                RmCommand rm = git.rm();
                for (String missingFile : missing) {
                    rm.addFilepattern(missingFile);
                }
                DirCache rmCache = rm.call();

                log.info("删除文件: getEntryCount: {},dirCache:{}", rmCache.getEntryCount(), rmCache);
            }


            Status status = git.status().call();

            boolean isChange = status.getAdded().size() > 0 ||
                    status.getChanged().size() > 0 ||
                    status.getMissing().size() > 0 ||
                    status.getAdded().size() > 0 ||
                    status.getModified().size() > 0 ||
                    status.getRemoved().size() > 0;
            if (!isChange) {
                log.info("未有改变，不提交:{}", status);
                return;
            }

            int version = getVersion(git);
            String message = String.format(commitTemplate, Integer.toString(version));

            log.info("开始提交！");
            RevCommit revCommit = git.commit().setAuthor(gitName, gitEmail).setMessage(message).call();
            log.info("提交结果:{}", revCommit);

            log.info("开始push:{}", revCommit);


            Iterable<PushResult> pushResults = git.push().setCredentialsProvider(cp).call();

            log.info("push结果:{}", Iterables.toString(pushResults));


        } finally {
            FileUtils.deleteDirectory(tempDir.toFile());
        }

    }

    private int getVersion(Git git) throws GitAPIException, MissingObjectException, IncorrectObjectTypeException {
        LogCommand log = git.log();
        List<Ref> refs = git.branchList().call();
        Ref curRef = null;
        String branch = "refs/heads/" + this.gitBranch;
        for (Ref ref : refs) {
            if (ref.getName().equals(branch)) {
                curRef = ref;
                break;
            }
        }
        if (curRef == null) {
            throw new RuntimeException("错误的分支:" + gitBranch);
        }

        log.add(curRef.getObjectId());

        Iterable<RevCommit> logs = log.call();
        int version = 0;
        for (RevCommit rev : logs) {
            version++;
        }
        return version;
    }

    @Override
    public String getOutPath() {
        return null;
    }

    public AbstractFileGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(AbstractFileGenerator generator) {
        this.generator = generator;
    }

    public String getGitUser() {
        return gitUser;
    }

    public void setGitUser(String gitUser) {
        this.gitUser = gitUser;
    }

    public String getGetPassword() {
        return getPassword;
    }

    public void setGetPassword(String getPassword) {
        this.getPassword = getPassword;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getGitBranch() {
        return gitBranch;
    }

    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
    }

    public String getSrcUri() {
        return srcUri;
    }

    public void setSrcUri(String srcUri) {
        this.srcUri = srcUri;
    }

    public List<String> getDeleteUris() {
        return deleteUris;
    }

    public void setDeleteUris(List<String> deleteUris) {
        this.deleteUris = deleteUris;
    }

    public String getCommitTemplate() {
        return commitTemplate;
    }

    public void setCommitTemplate(String commitTemplate) {
        this.commitTemplate = commitTemplate;
    }

    /**
     * 对应git  user.name
     */
    public String getGitName() {
        return gitName;
    }

    /**
     * 对应git  user.name
     */
    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    /**
     * 对应git  user.email
     */
    public String getGitEmail() {
        return gitEmail;
    }

    /**
     * 对应git  user.email
     */
    public void setGitEmail(String gitEmail) {
        this.gitEmail = gitEmail;
    }

    @Override
    public String toString() {
        return "GitGenerator{" +
                "generator=" + generator +
                ", gitUser='" + gitUser + '\'' +
                ", getPassword='" + getPassword + '\'' +
                ", gitName='" + gitName + '\'' +
                ", gitEmail='" + gitEmail + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", gitBranch='" + gitBranch + '\'' +
                ", srcUri='" + srcUri + '\'' +
                ", deleteUris='" + deleteUris + '\'' +
                ", commitTemplate='" + commitTemplate + '\'' +
                '}';
    }

    //    @Override
//    public void generate(Context context) throws Exception {
//        Path temp = null;
//        String srcPath = "javasdk/src/mian/java";
//        try{
//            temp = Files.createTempDirectory("apikit-git");
//            log.info("git临时目录:{}", temp.toAbsolutePath());
//
//            CredentialsProvider cp = new UsernamePasswordCredentialsProvider("zuoge85", "sbfgfg03423");
////                CredentialsProvider cp = new ChainingCredentialsProvider();
//            Git git = Git.cloneRepository()
//                    .setURI("https://code.aliyun.com/lipscoffee/cloud-sdk.git" )
//                    .setDirectory(temp.toFile())
//                    .setGitBranch("master")
//                    .setCredentialsProvider(cp)
//                    .call();
//
//
//            System.out.println(temp);
//            JavaClientGenerator generator = new JavaClientGenerator();
//            generator.setOutPath(new File(temp.toFile(), srcPath).getAbsolutePath());
//            generator.setVersion(version);
//            generator.setRootPackage("org.forkjoin.apikit.example.client");
//            generator.generate(context);
//
//            DirCache dirCache = git.add().addFilepattern(".").call();
//
//            Collection<ReflogEntry> call1 = git.reflog().call();
//            int size = call1.size();
//            List<Note> call = git.notesList().call();
//            RevCommit revCommit = git.commit().setAuthor("小草", "zuoge85@gmail.com").setAll(true).setMessage("更新版本!").call();
//
//            LogCommand log = git.log();
//
//
//            Ref ref = git.branchList().call().iterator().next();
//
//            log.add(ref.getObjectId());
//
//            Iterable<RevCommit> logs = log.call();
//            for (RevCommit rev : logs) {
//                System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
//            }
//
//            System.out.println(revCommit.toString());
//            Iterable<PushResult> pushResults = git.push().setCredentialsProvider(cp).call();
//            System.out.println(pushResults);
//            git.close();
//
//
//        }finally {
//            if(temp!=null){
//                temp.toFile().delete();
//            }
//        }
//
//    }
}
