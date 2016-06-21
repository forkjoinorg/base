package org.forkjoin.apikit.impl;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.PropertyInfo;
import org.forkjoin.apikit.info.TypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * message 分析器
 *
 * @author zuoge85 on 15/11/16.
 */
public class JdtMessageAnalyse extends JdtAbstractModuleAnalyse {
    private static final Logger log = LoggerFactory.getLogger(JdtMessageAnalyse.class);

    public JdtMessageAnalyse(JdtInfo jdtInfo) {
        super(jdtInfo);
    }

    @Override
    public ModuleInfo analyse() {
        MessageInfo messageInfo = new MessageInfo();
        initModuleInfo(messageInfo);

        messageInfo.addTypeParameters(jdtInfo.getTypeParameters());

        //处理字段
        FieldDeclaration[] fields = jdtInfo.getType().getFields();
        for (FieldDeclaration field : fields) {
            List fragments = field.fragments();
            if (fragments.size() != 1) {
                throw new AnalyseException("不能处理的情况");
            }
            VariableDeclarationFragment nameFragment = (VariableDeclarationFragment) fragments.get(0);
            String fieldName = nameFragment.getName().getFullyQualifiedName();

            TypeInfo typeInfo = jdtInfo.analyseType(field.getType());
            if (typeInfo == null) {
                throw new AnalyseException("类型解析失败!错误的字段:" + field.getType());
            }

            PropertyInfo propertyInfo = new PropertyInfo(fieldName, typeInfo);
            propertyInfo.setComment(transform(field.getJavadoc()));

            transformAnnotations(propertyInfo, field.modifiers());

            messageInfo.add(propertyInfo);
        }
        return messageInfo;
    }
}
