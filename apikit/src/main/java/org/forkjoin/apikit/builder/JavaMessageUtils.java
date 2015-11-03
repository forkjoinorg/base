package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.model.AttributeInfo;
import org.forkjoin.apikit.model.MessageInfo;
import org.forkjoin.apikit.model.ModelInfo;
import org.forkjoin.apikit.model.SupportType;
import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.TypeParameter;

import java.util.List;

/**
 * @author zuoge85 on 15/6/14.
 */
public class JavaMessageUtils extends JavaUtils {
    private MessageInfo messageInfo;

    public JavaMessageUtils(Config config, ModelInfo modelInfo, MessageInfo messageInfo, String rootPackage) {
        super(config, modelInfo, messageInfo, rootPackage);
        this.messageInfo = messageInfo;
    }

    @Override
    public void init() {
        super.init();
        addImport(getRootPackage() + ".ApiMessage");
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

    public String typeParameters() {
        List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
//
        if (CollectionUtils.isNotEmpty(typeParameters)) {
            StringBuilder sb = new StringBuilder("<");
//        <T extends ApiMessage>
            for (TypeParameter typeParameter : typeParameters) {
                if(sb.length() > 1){
                    sb.append(",");
                }
                sb.append(typeParameter.getName().getIdentifier()).append(" extends ApiMessage");
            }
            sb.append(">");
            return sb.toString();
        } else {
            return "";
        }
    }


    public String getTypeParametersField(String start) {
        List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
        StringBuilder sb = new StringBuilder();
        for (TypeParameter typeParameter : typeParameters) {
            String name = typeParameter.getName().getIdentifier();
            sb.append(start);
            sb.append("private Class<").append(name).append(">");
            sb.append(" ");
            sb.append(name.toLowerCase()).append("Cls;\n");
        }
        return sb.toString();
    }

    public String getTypeParametersAssign(String start) {
        List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
        StringBuilder sb = new StringBuilder();
        for (TypeParameter typeParameter : typeParameters) {
            String name = typeParameter.getName().getIdentifier();
            sb.append(start);
            sb.append("this.").append(name.toLowerCase()).append("Cls = ");
            sb.append(" ");
            sb.append(name.toLowerCase()).append("Cls;\n");
        }
        return sb.toString();
    }

//    $!{utils.toEncodeString("		", "parent", "map")

    public String getTypeParametersConstructorString() {
        List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
        StringBuilder sb = new StringBuilder();
        for (TypeParameter typeParameter : typeParameters) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            String name = typeParameter.getName().getIdentifier();
            sb.append("Class<").append(name).append(">");
            sb.append(" ");
            sb.append(name.toLowerCase()).append("Cls");
        }
        return sb.toString();
    }

    public String getConstructorString() {
        StringBuilder sb = new StringBuilder();
        for (AttributeInfo attr : messageInfo.getAttributes()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(attr.getSupportType().getNewJavaTypeString(false, false));
            sb.append(" ");
            sb.append(attr.getName());
        }
        return sb.toString();
    }

    //    $!{utils.getEncodeCode("        ")}
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
                        sb.append(start).append(" if (").append(name).append(" != null && (!").append(name).append(".isEmpty())) {\n");
                        sb.append(start).append("for (int i = 0; i < ").append(name).append(".size(); i++) {\n");
                        sb.append(start).append("    ").append(name).append(".get(i).encode(parent + \"")
                                .append(name).append("\" + \"[\" + i + \"].\", $list);\n");
                        sb.append(start).append("    }\n");
                        sb.append(start).append("}\n");
                    } else {
                        sb.append(start).append(" if (").append(name).append(" != null && (!").append(name).append(".isEmpty())) {\n");
                        sb.append(start).append("for (int i = 0; i < ").append(name).append(".size(); i++) {\n");
                        sb.append("$list.add(new SimpleImmutableEntry<String, Object>(parent + \"")
                                .append(name).append("\", ")
                                .append(name)
                                .append(".get(i)));\n");
                        sb.append(start).append("    }\n");
                        sb.append(start).append("}\n");
                    }
                } else {
                    sb.append(start).append(" if (").append(name).append(" != null) {\n");
                    sb.append(start).append("    ").append(name).append(".encode(parent + \"").append(name).append(".\", $list);");
                    sb.append(start).append("}\n");
                }
            } else {
                if (supportType.isHasNull()) {
                    sb.append(start).append("if (").append(name).append(" != null) {\n");
                    getEncodeCodeItemBase(start, sb, name);
                    sb.append(start).append("}\n");
                } else {
                    getEncodeCodeItemBase(start, sb, name);
                }
            }
        }
        return sb.toString();
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
                        sb.append(start).append(" if (").append(name).append(" != null && (!").append(name).append(".isEmpty())) {\n");
                        sb.append(start).append("for (int i = 0; i < ").append(name).append(".size(); i++) {\n");
                        sb.append(start).append("    ").append(name).append(".get(i).encode(parent + \"")
                                .append(name).append("\" + \"[\" + i + \"].\", $list);\n");
                        sb.append(start).append("    }\n");
                        sb.append(start).append("}\n");
                    } else {
                        sb.append(start).append(" if (").append(name).append(" != null && (!").append(name).append(".isEmpty())) {\n");
                        sb.append(start).append("for (int i = 0; i < ").append(name).append(".size(); i++) {\n");
                        sb.append("$list.add(new SimpleImmutableEntry<String, Object>(parent + \"")
                                .append(name).append("\", ")
                                .append(name)
                                .append(".get(i)));\n");
                        sb.append(start).append("    }\n");
                        sb.append(start).append("}\n");
                    }
                } else {
                    sb.append(start).append(" if (").append(name).append(" != null) {\n");
                    sb.append(start).append("    ").append(name).append(".encode(parent + \"").append(name).append(".\", $list);");
                    sb.append(start).append("}\n");
                }
            } else {
                if (supportType.isHasNull()) {
                    sb.append(start).append("if (").append(name).append(" != null) {\n");
                    getEncodeCodeItemBase(start, sb, name);
                    sb.append(start).append("}\n");
                } else {
                    getEncodeCodeItemBase(start, sb, name);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 基本类型
     */
    private void getEncodeCodeItemBase(String start, StringBuilder sb, String name) {
        sb.append(start)
                .append("    ")
                .append("$list.add(new SimpleImmutableEntry<String, Object>(parent + \"")
                .append(name).append("\",")
                .append(name)
                .append("));\n");
    }
//    if (id != null) {
//			$list.add(new SimpleImmutableEntry<String, Object>(parent + "id",id));
//		}
//		$list.add(new SimpleImmutableEntry<String, Object>(parent+ "booleanValue", booleanValue));
//
//		if (bytesValue != null) {
//			$list.add(new SimpleImmutableEntry<String, Object>(parent
//					+ "bytesValue", bytesValue));
//		}
//
//		if (booleanValueArray != null && (!booleanValueArray.isEmpty())) {
//			for (int i = 0; i < booleanValueArray.size(); i++) {
//				$list.add(new SimpleImmutableEntry<String, Object>(parent
//						+ "booleanValueArray", booleanValueArray.get(i)));
//			}
//		}
//
//		if (user != null) {
//			user.encode(parent + "user.", $list);
//		}
//
//		if (users != null && (!users.isEmpty())) {
//			for (int i = 0; i < users.size(); i++) {
//				users.get(i).encode(parent + "users" + "[" + i + "].", $list);
//			}
//		}
//
}
