package org.forkjoin.apikit.generator.apidoc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 对应  https://github.com/apidoc/apidoc-spec/blob/master/specs/v0.1.md#generator-object
 *
 * @author zuoge85@gmail.com on 2017/7/30.
 */
public class ApiDocProject {
    private String name;
    private String version;
    private String description;
    private String title;
    private String url;
    private String sampleUrl;

    private Generator generator;
    private Template template;
    private Header header;
    private Footer footer;


    public ApiDocProject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "ApiDocProject{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", sampleUrl='" + sampleUrl + '\'' +
                ", generator=" + generator +
                ", template=" + template +
                ", header=" + header +
                ", footer=" + footer +
                '}';
    }

    public static class Footer {
        private String title;
        private String content;

        public Footer() {
        }

        public Footer(String title, String content) {
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
            return "Header{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class Header {
        private String title;
        private String content;

        public Header() {
        }

        public Header(String title, String content) {
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
            return "Header{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class Template {
        private boolean withCompare;
        private boolean withGenerator;

        public Template() {
        }

        public Template(boolean withCompare, boolean withGenerator) {
            this.withCompare = withCompare;
            this.withGenerator = withGenerator;
        }

        public boolean isWithCompare() {
            return withCompare;
        }

        public void setWithCompare(boolean withCompare) {
            this.withCompare = withCompare;
        }

        public boolean isWithGenerator() {
            return withGenerator;
        }

        public void setWithGenerator(boolean withGenerator) {
            this.withGenerator = withGenerator;
        }

        @Override
        public String toString() {
            return "Template{" +
                    "withCompare='" + withCompare + '\'' +
                    ", withGenerator='" + withGenerator + '\'' +
                    '}';
        }
    }

    public static class Generator {
        private String version;
        private String time;

        public Generator() {
        }

        public Generator(String version, Date time) {
            this.version = version;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
            this.time = df.format(time);
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Generator{" +
                    "version='" + version + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}

