package org.forkjoin.apikit.oldmodel;

import org.eclipse.jdt.core.dom.TypeParameter;

import java.util.ArrayList;
import java.util.List;

public final class MessageInfo extends ModuleInfo {


    private ArrayList<AttributeInfo> attributes = new ArrayList<>();
    private List<TypeParameter> typeParameters = new ArrayList<>();

    public MessageInfo() {
        //this.pack = pack;
    }

    public void add(AttributeInfo attributeInfo) {
        attributes.add(attributeInfo);
    }

    public void addTypeParameter(TypeParameter typeParameter) {
        typeParameters.add(typeParameter);
    }

    public List<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    //	public String getJavaToString(){
////		return "TestAbc [testBool=" + testBool + ", testInt=" + testInt
////		+ ", testDouble=" + testDouble + ", testString=" + testString
////		+ "]";
//		StringBuilder sb = new StringBuilder();
//		sb.append('\"');
//		sb.append(name);
//		sb.append(" [");
//		for (AttributeInfo attr : attributes) {
//			sb.append(attr.getName());
//			sb.append("=\" + ");
//			sb.append(attr.getName());
//			sb.append(" + \"");
//			sb.append(',');
//		}
//		sb.append(" ]\"");
//		return sb.toString();
//	}
//
////	public String getJavaConstructorString(Utils utils){
////		StringBuilder sb = new StringBuilder();
////		for (AttributeInfo attr : attributes) {
////			if(sb.length() != 0){
////				sb.append(", ");
////			}
////			sb.append(utils.getJavaType(attr));
////			sb.append(" ");
////			sb.append(attr.getName());
////		}
////		return sb.toString();
////	}
////
////	public String getAsConstructorString(Utils utils){
////		StringBuilder sb = new StringBuilder();
////		for (AttributeInfo attr : attributes) {
////			if(sb.length() != 0){
////				sb.append(", ");
////			}
////			sb.append(attr.getName());
////			sb.append(":");
////			sb.append(utils.getAsTypeName(attr));
////		}
////		return sb.toString();
////	}
//
////	public String getAsToString(){
//////		return "TestAbc [testBool=" + testBool + ", testInt=" + testInt
//////		+ ", testDouble=" + testDouble + ", testString=" + testString
//////		+ "]";
////		StringBuilder sb = new StringBuilder();
////		sb.append('\"');
////		sb.append(name);
////		sb.append(" [");
////		for (AttributeInfo attr : attributes) {
////			sb.append(attr.getName());
////			sb.append("=\" + ");
////			if(attr.getType() == AttributeType.BYTES){
////				sb.append("ByteArrayUtils.toHexString(");
////				sb.append(attr.getAsFieldName());
////				sb.append(", 10)");
////			}else{
////				sb.append(attr.getAsFieldName());
////			}
////			sb.append(" + \"");
////			sb.append(',');
////		}
////		sb.append(" ]\"");
////		return sb.toString();
////	}
//	public void setComment(Javadoc comment) {
//		this.comment = comment;
//	}
//
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	protected void setAttributes(ArrayList<AttributeInfo> attributes) {
//		this.attributes = attributes;
//	}
//
//	public Javadoc getComment() {
//		return comment;
//	}
//
//
//
//	public String getName() {
//		return name;
//	}
//
////	public String getFieldName(){
////		return  Utils.toFieldName(name);
////	}
//
//	public ArrayList<AttributeInfo> getAttributes() {
//		return attributes;
//	}
//
//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public PackageInfo getPack() {
//		return pack;
//	}
//
//	public void setPack(PackageInfo pack) {
//		this.pack = pack;
//	}
//
//	public ArrayList<String> getJavaImports() {
//		return javaImports;
//	}
//
//	public void addJavaImport(String item){
//		javaImports.add(item);
//	}
//
//	public final String getPackageName() {
//		return packageName;
//	}
//
//	public final void setPackageName(String packageName) {
//		this.packageName = packageName;
//	}
//
//	@Override
//	public String toString() {
//		return "MessageInfo{" +
//				"javaImports=" + javaImports +
//				", comment='" + comment + '\'' +
//				", packageName='" + packageName + '\'' +
//				", name='" + name + '\'' +
//				", type=" + type +
//				", id=" + id +
//				", pack=" + pack +
//				", attributes=" + attributes +
//				'}';
//	}


    public ArrayList<AttributeInfo> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "attributes=" + attributes +
                '}';
    }
}
