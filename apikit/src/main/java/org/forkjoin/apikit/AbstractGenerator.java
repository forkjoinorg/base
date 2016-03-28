package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;

/**
 *
 */
public abstract class AbstractGenerator implements Generator {
    protected Context context;

    public void generate(Context context) throws Exception {
        this.context = context;
        context.apis.getValues().parallelStream().forEach(
                apiInfo -> {
                    try {
                        generateApi(apiInfo);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        context.messages.getValues().parallelStream().forEach(
                messageInfo -> {
                    try {
                        generateMessage(messageInfo);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        generateTool();
    }

    public abstract void generateApi(ApiInfo apiInfo) throws Exception;

    public abstract void generateMessage(MessageInfo messageInfo) throws Exception;

    public abstract void generateTool() throws Exception;
}
