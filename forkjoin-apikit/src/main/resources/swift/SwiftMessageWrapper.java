package org.forkjoin.apikit.builder.swift;

import org.forkjoin.apikit.old.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.forkjoin.apikit.oldmodel.*;

import java.util.List;

/**
 * @author zuoge85 on 15/6/14.
 */
public class SwiftMessageWrapper extends SwiftWrapper {
    private MessageInfo messageInfo;

    public SwiftMessageWrapper(Config config, ModelInfo modelInfo, MessageInfo messageInfo, String rootPackage) {
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


//    var tel:String!
//    var pass:String!
//    var timestamp:String!
//    var sign:String!
//    var login:LoginViewController!
//    var tels:[String]!
//    var logins:[LoginViewController]!

    public String property(AttributeInfo attr) {
        StringBuilder sb = new StringBuilder();
        SupportType supportType = attr.getSupportType();

//        String typeString = getTypeString(supportType);
//        AttributeType type = supportType.getType();
        String swiftType = supportType.getSwiftTypeString(config.getSwiftClientClassPrefix(), true);

        if(AttributeType.isBaseType(supportType.getType())) {
            sb.append("var ").append(attr.getName()).append(":").append(swiftType).append("\n");
        } else {
            sb.append("var ").append(attr.getName()).append(":").append(swiftType).append("?\n");
        }
        return sb.toString();
    }




//

//    public String ocClass(String lineStart) {
//        StringBuilder sb = new StringBuilder();
//        ArrayList<AttributeInfo> attributes = messageInfo.getAttributes();
//        LinkedHashSet<String> names = new LinkedHashSet<>();
//        for (int i = 0; i < attributes.size(); i++) {
//            AttributeInfo info = attributes.get(i);
//            SupportType supportType = info.getSupportType();
//            if(supportType.isApiType()){
//                names.add(supportType.getName());
//            }
//        }
//        for (String name : names) {
//            sb.append(lineStart);
//            sb.append("@class ").append(getOcName(name)).append(";\n");
//        }
//        return sb.toString();
//    }

    public String typeParameters() {
        List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
//
        if (CollectionUtils.isNotEmpty(typeParameters)) {
            StringBuilder sb = new StringBuilder("<");
//        <T extends ApiMessage>
            for (TypeParameter typeParameter : typeParameters) {
                sb.append(typeParameter.getName().getIdentifier()).append(":ApiObject");
            }
            sb.append(">");
            return sb.toString();
        } else {
            return "";
        }
    }


    public String getEncodeCode(String start) {
        StringBuilder sb = new StringBuilder();
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            sb.append('\n');
            SupportType supportType = attr.getSupportType();
            String name = attr.getName();
            if (supportType.isOtherType()) {
                List<SupportType> typeArguments = supportType.getTypeArguments();
                if (supportType.isArray()) {
                    SupportType listInnerSupportType = typeArguments.get(0);
                    if (listInnerSupportType.isOtherType()) {
                        sb.append(start).append("if let ").append(name).append(" = ").append(name).append("{\n");
                        sb.append(start).append("    ").append("for (_index, _value) in enumerate(").append(name)
                                .append(") {\n");

                        sb.append(start).append("        ").append("_value.encode(\n");

                        sb.append(start).append("            ").append("\"\\(_parent)").append(name)
                                .append("[\\(_index)].\",&_form\n");

                        sb.append(start).append("        ").append(")\n");

                        sb.append(start).append("    ").append("}\n");
                        sb.append(start).append("}\n");
                    } else {
                        sb.append(start).append("if let ").append(name).append(" = ").append(name).append("{\n");

                        sb.append(start).append("    ").append("_form[\"\\(_parent)").append(name).append("\"] = ")
                                .append(name).append(".map{return ApiToString($0)}\n");

                        sb.append(start).append("}\n");
                    }
                } else {
                    sb.append(start).append("if let ").append(name).append(" = ").append(name).append(" {\n");

                    sb.append(start).append("    ").append(name).append(".encode(_parent + \"").append(name)
                            .append(".\", &_form)\n");

                    sb.append(start).append("}\n");
                }
            } else {
                getEncodeCodeItemBase(start, sb, name, attr);
            }
        }
        return sb.toString();
    }


    /**
     * 基本类型
     */
    private void getEncodeCodeItemBase(String start, StringBuilder sb, String name, AttributeInfo attr) {
        String step = StringUtils.EMPTY;
        if(!attr.getSupportType().isBaseType()){
            step = "    ";
            sb.append(start).append("if let ").append(name).append(" = ").append(name).append("{\n");
        }


        sb.append(start)
                .append(step).append("_form[\"\\(_parent)").append(name)
                .append("\"] = ApiToString(").append(name).append(")\n");

        if(!attr.getSupportType().isBaseType()){
            sb.append(start).append("}\n");
        }
    }

    public String baseInit(String start) {
        StringBuilder sb = new StringBuilder(start).append("\n");
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            if(attr.getSupportType().isBaseType()){
                sb.append(start).append("self." + attr.getName() + " = " + attr.getSupportType().getSwiftDefault()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getDecodeCode(String start) {
        StringBuilder sb = new StringBuilder();
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            sb.append('\n');
            SupportType supportType = attr.getSupportType();
            String name = attr.getName();
            if (supportType.isOtherType()) {
                List<SupportType> typeArguments = supportType.getTypeArguments();
                if (supportType.isArray()) {
                    SupportType listInnerSupportType = typeArguments.get(0);


                    if (listInnerSupportType.isOtherType()) {

//                        if let users = _decoder["users"].array {
//                            self.users = Array<APIUser>()
//                            for _item in users {
//                                self.users!.append(APIUser(_item))
//                            }
//                        }
//                        String swiftType = AttributeType.toSwiftType(listInnerSupportType.getType());
                        String swiftType = listInnerSupportType.getSwiftTypeString(config.getSwiftClientClassPrefix(), true);

                        sb.append(start).append("if let " + name + " = _decoder[\"" + name + "\"].array {\n");

                        sb.append(start).append("    ").append("self." + name + " = Array<" + swiftType + ">()\n");
                        sb.append(start).append("    ").append("for _item in " + name + " {\n");

                        sb.append(start).append("        ").append("self." + name + "!.append(" + swiftType + "(_item))\n");

                        sb.append(start).append("    ").append("}\n");

                        sb.append(start).append("}\n");
                    } else {
                        String swiftType = AttributeType.toSwiftType(listInnerSupportType.getType());
//                        if let booleanValueArray = _decoder["booleanValueArray"].array {
//                            self.booleanValueArray = Array<Bool>()
//                            for _item in booleanValueArray {
//                                if let _value:Bool  = ApiTransform(_item){
//                                    self.booleanValueArray.append(true)
//                                }
//                            }
//                        }
                        sb.append(start).append("if let " + name + " = _decoder[\"" + name + "\"].array {\n");
                        sb.append(start).append("    ").append("self." + name + " = Array<" + swiftType + ">()\n");
                        sb.append(start).append("    ").append("for _item in " + name + " {\n");


                        sb.append(start).append("        ").append("if let _value:" + swiftType + "  = ApiTransform(_item){\n");

                        sb.append(start).append("            ").append("self." + name + "!.append(_value)\n");

                        sb.append(start).append("        ").append("}\n");


                        sb.append(start).append("    ").append("}\n");
                        sb.append(start).append("}\n");
                    }
                } else {

//                    var user = _decoder["user"]
//                    if user.error != nil {
//                        self.user = APIUser(user)
//                    }
                    sb.append(start).append("var " + name + " = _decoder[\"" + name + "\"]\n");
                    sb.append(start).append("if " + name + ".error == nil {\n");

                    String swiftType = supportType.getSwiftTypeString(config.getSwiftClientClassPrefix(), true);
                    sb.append(start).append("    ").append("self." + name + " = " + swiftType + "(" + name + ")\n");

                    sb.append(start).append("}\n");
                }
            } else {
                getDecodeCodeItemBase(start, sb, name, attr);
            }
        }
        return sb.toString();
    }

    private void getDecodeCodeItemBase(String start, StringBuilder sb, String name, AttributeInfo attr) {
//        sb.append(start).append("if let ").append(name).append(" = ").append(name).append("{\n");


        sb.append(start)
                .append("self.")
                .append(name).append(" = ApiTransform(_decoder[\"")
                .append(name).append("\"])");
        if(attr.getSupportType().isBaseType()){
            sb.append("!");
        }

//        sb.append(start).append("}\n");
    }
}
