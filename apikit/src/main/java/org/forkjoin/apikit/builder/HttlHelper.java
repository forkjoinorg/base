package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.old.JavaFileFormat;
import httl.Engine;
import httl.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

/**
 * @author zuoge85 on 15/6/14.
 */
public class HttlHelper {
    private static final Logger log = LoggerFactory.getLogger(HttlHelper.class);
    public static final String ENCODING = "utf8";

    public static Engine getEngine() {
        return Inner.engine;
    }


    public static class Inner {
        private static final Engine engine;

        static {
            Properties configProperties = new Properties();
            try {
                configProperties.load(HttlHelper.class.getResourceAsStream("/org/forkjoin/apikit/httl.properties"));
                engine = Engine.getEngine(configProperties);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void renderFile(
            File file, Map<String, Object> parameters, String templPath,
            boolean isFormatJava
    ) throws IOException, ParseException {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new RuntimeException("怎么。创建路径失败");
            }
        }

        String code = renderToString(parameters, templPath);
        if (isFormatJava) {
            String formatCode = JavaFileFormat.formatCode(code);
            writeStringToFile(file, formatCode, ENCODING);
            log.info("生成一个文件{}", file.getAbsolutePath());
        } else {
            writeStringToFile(file, code, ENCODING);
            log.info("生成一个文件{}", file.getAbsolutePath());
        }
    }

    public static void writeStringToFile(File file, String data, String encoding) throws IOException {
        data = data.replaceAll("(\r\n|\r|\n|\n\r)", "\r\n");
        FileUtils.writeStringToFile(file, data, encoding);
    }

    public static String renderToString(Map<String, Object> parameters, String templPath) throws IOException, ParseException {
        StringBuilderWriter writer = new StringBuilderWriter();
        Template template = Inner.engine.getTemplate(templPath);
        template.render(parameters, writer);
        log.info("生成一个文件到字符串");
        return writer.toString();
    }
}
