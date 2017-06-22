package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.AbstractGenerator;
import org.forkjoin.apikit.utils.HttlUtils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class HttlGenerator extends AbstractGenerator {
    private static final Logger log = LoggerFactory.getLogger(HttlGenerator.class);


    protected void executeModule(
            BuilderWrapper utils, String templPath, File file
    )
            throws Exception {

        log.info("开始生成文件:{}, templ:{}", file.getAbsolutePath(), templPath);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", utils.getModuleInfo());
        parameters.put("utils", utils);
        HttlUtils.renderFile(file, parameters, templPath);
        log.info("结束生成文件:{}, templ:{}", file.getAbsolutePath(), templPath);
    }

    protected void execute(
            Map<String, Object> parameters, String templPath, File file
    )
            throws Exception {

        log.info("开始生成文件:{}, templ:{}", file.getAbsolutePath(), templPath);
        HttlUtils.renderFile(file, parameters, templPath);
        log.info("结束生成文件:{}, templ:{}", file.getAbsolutePath(), templPath);
    }


    protected String executeModuleToString(
            BuilderWrapper utils, String templPath
    )
            throws Exception {

        log.info("开始生成文件到内存 name:{}, templ:{}", utils.getFullName(), templPath);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", utils.getModuleInfo());
        parameters.put("utils", utils);
        String code = HttlUtils.renderToString(parameters, templPath);
        log.info("结束生成文件到内存 name:{}, templ:{}", utils.getFullName(), templPath);
        return code;
    }
}
