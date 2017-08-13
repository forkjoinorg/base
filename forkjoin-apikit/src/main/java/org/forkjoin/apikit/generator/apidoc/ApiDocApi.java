package org.forkjoin.apikit.generator.apidoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对应文档
 *
 * @author zuoge85@gmail.com on 2017/7/30.
 */
public class ApiDocApi {
    private String type;
    private String url;
    private String title;
    private String version;
    private String name;
    private String group;
    private String filename;
    private String description;
    private Permission permission;
    private List<Examples> examples;
    private Fields parameter;
    private Fields success;
    private Fields error;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public List<Examples> getExamples() {
        return examples;
    }

    public void setExamples(List<Examples> examples) {
        this.examples = examples;
    }

    public Fields getParameter() {
        return parameter;
    }

    public void setParameter(Fields parameter) {
        this.parameter = parameter;
    }

    public Fields getSuccess() {
        return success;
    }

    public void setSuccess(Fields success) {
        this.success = success;
    }

    public Fields getError() {
        return error;
    }

    public void setError(Fields error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ApiDocApi{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", filename='" + filename + '\'' +
                ", description='" + description + '\'' +
                ", permission=" + permission +
                ", examples=" + examples +
                ", parameter=" + parameter +
                ", success=" + success +
                ", error=" + error +
                '}';
    }

    public static class Permission {
        private String name;
        private String title;
        private String description;

        public Permission() {
        }

        public Permission(String name, String title, String description) {
            this.name = name;
            this.title = title;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Permission{" +
                    "name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public static class Examples {
        private String title;
        private String content;

        public Examples() {
        }

        public Examples(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Examples{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }


    public static class Fields {
        private Map<String, List<Field>> fields;

        public Fields() {
        }

        public Fields(Map<String, List<Field>> fields) {
            this.fields = fields;
        }

        public Map<String, List<Field>> getFields() {
            return fields;
        }

        public void setFields(HashMap<String, List<Field>> fields) {
            this.fields = fields;
        }

        public void add(String name, List<Field> fieldItems) {
            if (this.fields == null) {
                this.fields = new HashMap<>();
            }
            this.fields.put(name, fieldItems);
        }

        @Override
        public String toString() {
            return "Fields{" +
                    "fields=" + fields +
                    '}';
        }
    }

    public static class Field {
        private String group;
        private String type;
        private String field;
        private boolean optional;
        private String description;
        private String defaultValue;

        public Field() {

        }

        public Field(String group, String type, String field, boolean optional, String description, String defaultValue) {
            this.group = group;
            this.type = type;
            this.field = field;
            this.optional = optional;
            this.description = description;
            this.defaultValue = defaultValue;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public boolean isOptional() {
            return optional;
        }

        public void setOptional(boolean optional) {
            this.optional = optional;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "group='" + group + '\'' +
                    ", type='" + type + '\'' +
                    ", field='" + field + '\'' +
                    ", optional='" + optional + '\'' +
                    ", description='" + description + '\'' +
                    ", defaultValue='" + defaultValue + '\'' +
                    '}';
        }
    }


}
