package org.forkjoin.apikit.builder.oc;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * @author zuoge85 on 15/6/14.
 */
public class OcMessageWrapper extends OcWrapper {
    private MessageInfo messageInfo;

    public OcMessageWrapper(Config config, ModelInfo modelInfo, MessageInfo messageInfo, String rootPackage) {
        super(config, modelInfo, messageInfo, rootPackage);
        this.messageInfo = messageInfo;
    }

    public String getToString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\"');
        sb.append(messageInfo.getName());
        sb.append(" [");
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            sb.append(attr.getName());
            sb.append("=\" + ");
            sb.append(attr.getName());
            sb.append(" + \"");
            sb.append(',');
        }
        sb.append(" ]\"");
        return sb.toString();
    }


//    $!{utils.toEncodeString("		", "parent", "map")

    public String getConstructorString() {
        StringBuilder sb = new StringBuilder();
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(getTypeString(attr.getSupportType()));
            sb.append(" ");
            sb.append(attr.getName());
        }
        return sb.toString();
    }


//    @property (copy, nonatomic) NSString *name;
//    @property (assign, nonatomic) BOOL gay;


//    @property (strong, nonatomic) NSNumber *money;
//    @property (strong, nonatomic) User *user;
//    @property (strong, nonatomic) Status *retweetedStatus;
//    @property (strong, nonatomic) NSArray *ads;

    public String property(AttributeInfo attr) {
        StringBuilder sb = new StringBuilder();
        SupportType supportType = attr.getSupportType();

        String typeString = getTypeString(supportType);
        AttributeType type = supportType.getType();
        String ocType = AttributeType.toOcType(type);
        if(supportType.isArray()){
            sb.append("@property (assign, nonatomic) ").append(typeString).append(" *").append(attr.getName()).append(";\n");
        } else if(type == AttributeType.BOOLEAN) {
            sb.append("@property (assign, nonatomic) ").append(typeString).append(" ").append(attr.getName()).append(";\n");
        } else if(type == AttributeType.STRING) {
            sb.append("@property (copy, nonatomic) ").append(typeString).append(" *").append(attr.getName()).append(";\n");
        } else if(type == AttributeType.OTHER){
            sb.append("@property (strong, nonatomic) ").append(typeString).append(" *").append(attr.getName()).append(";\n");
        } else{
            sb.append("@property (strong, nonatomic) ").append(typeString).append(" *").append(attr.getName()).append(";\n");
        }
        return sb.toString();
    }


//

    public String ocClass(String lineStart) {
        StringBuilder sb = new StringBuilder();
        ArrayList<AttributeInfo> attributes = messageInfo.getAttributes();
        LinkedHashSet<String> names = new LinkedHashSet<>();
        for (int i = 0; i < attributes.size(); i++) {
            AttributeInfo info = attributes.get(i);
            SupportType supportType = info.getSupportType();
            if(supportType.isApiType()){
                names.add(supportType.getName());
            }
        }
        for (String name : names) {
            sb.append(lineStart);
            sb.append("@class ").append(getOcName(name)).append(";\n");
        }
        return sb.toString();
    }
}
