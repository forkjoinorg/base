package org.forkjoin.apikit.builder.swift;

import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.oldmodel.AttributeType;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.oldmodel.ModuleInfo;
import org.forkjoin.apikit.oldmodel.SupportType;

/**
 * @author zuoge85 on 15/6/14.
 */
public class SwiftUtils extends BuilderUtils {

    public SwiftUtils(Config config, ModelInfo modelInfo, ModuleInfo moduleInfo, String rootPackage) {
        super(config, modelInfo, moduleInfo, rootPackage);
    }

    @Override
    public String getName(){
        return getSwiftName(moduleInfo.getName());
    }


    public String getSwiftName(String javaTypeName) {
        return config.getSwiftClientClassPrefix() + javaTypeName;
    }

    public String getTypeString(SupportType supportType, boolean isWrap, boolean isArrayList) {
        String listType = "NSArray";
        AttributeType attributeType = supportType.getType();
        if (attributeType == AttributeType.OTHER) {
            if (supportType.isArray()) {
                return listType;
            } else {
                return getSwiftName(supportType.getName());
            }
        } else {
            if (supportType.isArray()) {
                return listType;
            } else {
                return AttributeType.toSwiftType(attributeType);
            }
        }
    }



    @Override
    public String comment(String start) {
        return super.comment(start);
    }
}
