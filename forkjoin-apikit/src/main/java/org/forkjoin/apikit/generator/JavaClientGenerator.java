package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.wrapper.JSWrapper;
import org.forkjoin.apikit.wrapper.JavaClientApiWrapper;
import org.forkjoin.apikit.wrapper.JavaMessageWrapper;

import java.io.File;
import java.util.regex.Pattern;

/**
 *
 */
public class JavaClientGenerator extends JavaGenerator {
    private boolean isAnnotations = false;
    private boolean isReactive = false;

    private NameMaper apiNameMaper = new PatternNameMaper(
            "(?<name>.*)Controller","${name}"
    );

    public JavaClientGenerator() {
    }

    protected JavaMessageWrapper createMessageWarpper(MessageInfo messageInfo) {
        JavaMessageWrapper javaMessageWrapper = new JavaMessageWrapper(context, messageInfo, rootPackage);
        javaMessageWrapper.setAnnotations(isAnnotations);
        return javaMessageWrapper;
    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JavaClientApiWrapper utils = new JavaClientApiWrapper(
                context, apiInfo, rootPackage, apiNameMaper
        );
        utils.setAnnotations(isAnnotations);
        File file = getFileName(utils);
        utils.init();
        if(isReactive){
            executeModule(
                    utils,
                    "/org/forkjoin/apikit/generator/ReactiveApiItem.httl",
                    file
            );
        }else{
            executeModule(
                    utils,
                    "/org/forkjoin/apikit/generator/ApiItem.httl",
                    file
            );
        }
    }


    @Override
    public void generateTool() throws Exception {
    }


    public boolean isAnnotations() {
        return isAnnotations;
    }

    public void setAnnotations(boolean annotations) {
        isAnnotations = annotations;
    }

    public NameMaper getApiNameMaper() {
        return apiNameMaper;
    }

    public void setApiNameMaper(NameMaper apiNameMaper) {
        this.apiNameMaper = apiNameMaper;
    }

    public boolean isReactive() {
        return isReactive;
    }

    public void setReactive(boolean reactive) {
        isReactive = reactive;
    }
}
