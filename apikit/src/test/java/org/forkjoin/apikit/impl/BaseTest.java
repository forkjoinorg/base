package org.forkjoin.apikit.impl;

import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.info.Import;
import org.forkjoin.apikit.info.JavadocInfo;
import org.forkjoin.apikit.info.TypeInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author zuoge85 on 15/12/9.
 */
public class BaseTest {
    protected String readCode(String name) throws IOException {
        InputStream input = JdtApiModuleAnalyseTest.class.getResourceAsStream(name);
        return IOUtils.toString(input);
    }

    protected Analyse createAnalyse() {
        return new JdtAnalyse();
    }


    protected void assertImport(Import anImport, String name) {
        assertImport(anImport, name, false);
    }

    protected void assertImport(Import anImport, String name, boolean isInside) {
        assertEquals(anImport.getFullName(), name);
        assertEquals(anImport.isInside(), isInside);
    }

    protected void assertType(
            TypeInfo type0, String fullName
    ) {
        assertType(type0, fullName, false);
    }

    protected void assertType(
            TypeInfo type0, String fullName, boolean isInside
    ) {
        assertType(type0, fullName, isInside, false);
    }

    protected void assertType(
            TypeInfo type0, String fullName, boolean isInside, boolean isArray
    ) {
        assertType(type0, fullName, isInside, isArray, 0);
    }



    protected void assertType(
            TypeInfo type0, String fullName, boolean isInside,
            boolean isArray, int typeArguments
    ) {
        if(fullName != null){
            assertEquals(type0.getFullName(), fullName);
            assertEquals(type0.getType(), TypeInfo.Type.OTHER);
        }
        assertEquals(type0.isInside(), isInside);
        assertEquals(type0.isArray(), isArray);
        assertEquals(type0.getTypeArguments().size(), typeArguments);
    }


    protected void assertJavadocInfo(
            JavadocInfo comment, String name,List<String> value
    ) {
        Map.Entry<String, List<String>> entry = comment.getTags().get(0);

        assertEquals(entry.getKey(), name);
        assertEquals(entry.getValue(), value);
    }


    protected void assertJavadocInfo(
            JavadocInfo comment, String name0, List<String> value0,String name1, List<String> value1
    ) {
        Map.Entry<String, List<String>> entry = comment.getTags().get(0);

        assertEquals(entry.getKey(), name0);
        assertEquals(entry.getValue(), value0);

        entry = comment.getTags().get(1);

        assertEquals(entry.getKey(), name1);
        assertEquals(entry.getValue(), value1);
    }
}
