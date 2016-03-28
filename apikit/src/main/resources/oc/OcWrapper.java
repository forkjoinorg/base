package org.forkjoin.apikit.builder.oc;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.oldmodel.AttributeType;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.oldmodel.ModuleInfo;
import org.forkjoin.apikit.oldmodel.SupportType;

/**
 * @author zuoge85 on 15/6/14.
 */
public class OcWrapper extends BuilderWrapper {

    public OcWrapper(Config config, ModelInfo modelInfo, ModuleInfo moduleInfo, String rootPackage) {
        super(config, modelInfo, moduleInfo, rootPackage);
    }

    @Override
    public String getName(){
        return getOcName(moduleInfo.getName());
    }


    public String getOcName(String javaTypeName) {
        return config.getOcClientClassPrefix() + javaTypeName;
    }

    public String getTypeString(SupportType supportType, boolean isWrap, boolean isArrayList) {
        String listType = "NSArray";
        AttributeType attributeType = supportType.getType();
        if (attributeType == AttributeType.OTHER) {
            if (supportType.isArray()) {
                return listType;
            } else {
                return getOcName(supportType.getName());
            }
        } else {
            if (supportType.isArray()) {
                return listType;
            } else {
                return AttributeType.toOcType(attributeType);
            }
        }
    }
}
