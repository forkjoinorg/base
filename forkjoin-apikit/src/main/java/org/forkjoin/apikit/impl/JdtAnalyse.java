package org.forkjoin.apikit.impl;

import org.eclipse.jdt.core.dom.Annotation;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Message;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.ModuleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 基于jdt的分析器
 *
 * @author zuoge85 on 15/11/8.
 */
public class JdtAnalyse implements Analyse {
    private static final Logger log = LoggerFactory.getLogger(JdtAnalyse.class);

    @Override
    public ModuleInfo analyse(String code, String packageName) {
        JdtInfo jdtInfo = JdtInfo.parser(code, packageName);
        if(jdtInfo == null){
            return null;
        }


        ModuleType moduleType = analyseType(jdtInfo);

        log.debug("模块类型{},fullName:{}",moduleType, jdtInfo.getFullName());

        if (moduleType == null) {
            return null;
        }

        JdtAbstractModuleAnalyse moduleAnalyse = createModuleAnalyse(moduleType, jdtInfo);
        if (moduleAnalyse != null) {
            return moduleAnalyse.analyse();
        } else {
            throw new AnalyseException("错误的模块,未找到模块解析类:" + moduleType);
        }
    }

    private ModuleType analyseType(JdtInfo jdtInfo) {
        List modifiers = jdtInfo.getType().modifiers();
        for (Object o : modifiers) {
            if (o instanceof Annotation) {
                Annotation annotation = (Annotation) o;
                String fullName = jdtInfo.getFullTypeName(annotation.getTypeName());

                if (fullName.equals(Api.class.getName())) {
                    return ModuleType.API;
                } else if (fullName.equals(Message.class.getName())) {
                    return ModuleType.MESSAGE;
                } else if (fullName.equals(org.forkjoin.apikit.core.Enum.class.getName())) {
                    return ModuleType.ENUM;
                }
            }
        }
        return null;
    }

    protected JdtAbstractModuleAnalyse createModuleAnalyse(ModuleType type, JdtInfo jdtInfo) {
        switch (type) {
            case API:
                return new JdtApiAnalyse(jdtInfo);
            case MESSAGE:
                return new JdtMessageAnalyse(jdtInfo);
            default:
                return null;
        }
    }
}
