package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.model.MessageInfo;
import org.forkjoin.apikit.model.ModelInfo;
import org.eclipse.jdt.core.dom.Annotation;

import java.util.ArrayList;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JavaAndroidMessageUtils extends  JavaMessageUtils {

    public JavaAndroidMessageUtils(Config config, ModelInfo modelInfo, MessageInfo messageInfo, String rootPackage) {
        super(config, modelInfo, messageInfo, rootPackage);
    }

    @Override
    public String formatAnnotations(ArrayList<Annotation> annotations, String start) {
        return "";
    }


}
