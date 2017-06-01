package org.forkjoin.apikit.utils;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import java.util.Map;

/**
 * @author zuoge85 on 15/6/16.
 */
public class JavaFileFormat {

    public static final CodeFormatter CODE_FORMATTER = createCodeFormatter();

    @SuppressWarnings("unchecked")
    private static CodeFormatter createCodeFormatter() {
        Map options = JavaCore.getOptions();
        JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
        options.put("org.eclipse.jdt.core.formatter.lineSplit", "120");
        return ToolFactory.createCodeFormatter(options);
    }

    public synchronized static String formatCode(String contents) {
        IDocument doc = new Document(contents);
        TextEdit edit = CODE_FORMATTER.format(
                CodeFormatter.K_COMPILATION_UNIT, doc.get(), 0, doc.get()
                        .length(), 0, null);


        if (edit != null) {
            try {
                edit.apply(doc);
                contents = doc.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return contents;
    }
}
