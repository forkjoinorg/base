package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;

/**
 *
 */
public abstract class AbstractGenerator extends AbstractFileGenerator {
    protected Context context;

    private String version;

    public void generate(Context context) throws Exception {
        this.context = context;

        for (ApiInfo apiInfo : context.apis.getValues()) {
            try {
                generateApi(apiInfo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        for (MessageInfo messageInfo : context.messages.getValues()) {
            try {
                generateMessage(messageInfo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        generateTool();
    }

    public abstract void generateApi(ApiInfo apiInfo) throws Exception;

    public abstract void generateMessage(MessageInfo messageInfo) throws Exception;

    public abstract void generateTool() throws Exception;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
