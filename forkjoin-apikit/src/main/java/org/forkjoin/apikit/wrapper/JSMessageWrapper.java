package org.forkjoin.apikit.wrapper;

import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.info.PropertyInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zuoge85 on 15/6/14.
 */
public class JSMessageWrapper extends JSWrapper<MessageInfo> {
    public JSMessageWrapper(Context context, MessageInfo messageInfo, String rootPackage) {
        super(context, messageInfo, rootPackage);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public String getImports() {
        Set<String> containsSet = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        //自己的目录级别
        int i = 0;
        for (PropertyInfo pro : moduleInfo.getProperties()) {
            if (moduleInfo.getPackageName().equals(pro.getTypeInfo().getPackageName())) {
                if (i == 0) {
                    sb.append("\n\n");
                }
                String proTypeName = pro.getTypeInfo().getName();
                if (!this.getName().equals(proTypeName)) {
                    if (!containsSet.contains(proTypeName)) {
                        containsSet.add(proTypeName);
                        sb.append("import ")
                                .append(proTypeName)
                                .append(" from './").append(proTypeName).append("'\n");
                        i++;
                    }
                }
            }
        }
        return sb.toString();
    }

    public String toObjectArgs() {
        StringBuilder sb = new StringBuilder();
        ArrayList<PropertyInfo> properties = moduleInfo.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            PropertyInfo info = properties.get(i);
            if (i > 0) {
                sb.append(',');
            }
            sb.append(info.getName());
        }
        return sb.toString();
    }

    public String toObjectArgsType() {
        StringBuilder sb = new StringBuilder();
        ArrayList<PropertyInfo> properties = moduleInfo.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            PropertyInfo info = properties.get(i);
            if (i > 0) {
                sb.append(',');
            }
            sb.append(info.getName());
            sb.append(":");
            sb.append(toTypeString(info.getTypeInfo()));
        }
        return sb.toString();
    }
}
