package org.forkjoin.apikit.info;

import java.util.*;

public final class MessageInfo extends ModuleInfo {
    private ArrayList<PropertyInfo> properties = new ArrayList<>();
    private Map<String, PropertyInfo> propertiesMap = new HashMap<>();

    private List<String> typeParameters = new ArrayList<>();

    public MessageInfo() {
        
    }

    public void add(PropertyInfo attributeInfo) {
        properties.add(attributeInfo);
        propertiesMap.put(attributeInfo.getName(), attributeInfo);
    }

    public PropertyInfo getProperty(String name) {
        return propertiesMap.get(name);
    }

    public ArrayList<PropertyInfo> getProperties() {
        return properties;
    }

    public void addTypeParameter(String typeParameter) {
        typeParameters.add(typeParameter);
    }


    public void addTypeParameters(Collection<String> collections) {
        typeParameters.addAll(collections);
    }

    public List<String> getTypeParameters() {
        return typeParameters;
    }


    @Override
    public String toString() {
        return super.toString() + ",MessageInfo{" +
                "properties=" + properties.size() +
                ", propertiesMap=" + propertiesMap.size() +
                ", typeParameters=" + typeParameters.size() +
                '}';
    }
}
