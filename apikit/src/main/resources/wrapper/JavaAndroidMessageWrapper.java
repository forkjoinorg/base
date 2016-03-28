package wrapper;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.MessageInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.eclipse.jdt.core.dom.Annotation;

import java.util.ArrayList;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JavaAndroidMessageWrapper extends JavaMessageWrapper {

    public JavaAndroidMessageWrapper(Config config, ModelInfo modelInfo, MessageInfo messageInfo, String rootPackage) {
        super(config, modelInfo, messageInfo, rootPackage);
    }

    @Override
    public String formatAnnotations(ArrayList<Annotation> annotations, String start) {
        return "";
    }


}
