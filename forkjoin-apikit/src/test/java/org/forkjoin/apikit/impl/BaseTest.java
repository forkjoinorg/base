package org.forkjoin.apikit.impl;

import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.info.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author zuoge85 on 15/12/9.
 */
public class BaseTest {
    protected String readCode(String name) throws IOException {
        InputStream input = BaseTest.class.getResourceAsStream(name);
        return IOUtils.toString(input);
    }

    protected Analyse createAnalyse() {
        return new JdtAnalyse();
    }


    protected void assertImport(Import anImport, String name) {
        assertImport(anImport, name, false);
    }

    protected void assertImport(Import anImport, String name, boolean isInside) {
        assertEquals(name, anImport.getFullName());
        assertEquals(isInside, anImport.isInside());
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
        if (fullName != null) {
            assertEquals(fullName, type0.getFullName());
            assertEquals(TypeInfo.Type.OTHER, type0.getType());
        }
        assertEquals(isInside, type0.isInside());
        assertEquals(isArray, type0.isArray());
        assertEquals(typeArguments, type0.getTypeArguments().size());
    }


    protected void assertJavadocInfo(
            JavadocInfo comment, String name, List<String> value
    ) {
        Map.Entry<String, Collection<List<String>>> entry = comment.getTags(0);
        assertEquals(name, entry.getKey());
        assertEquals(Collections.singletonList(value), entry.getValue());
    }


    protected void assertJavadocInfo(
            JavadocInfo comment, String name0, List<String> value0, String name1, List<String> value1
    ) {
        Map.Entry<String, Collection<List<String>>> entry = comment.getTags(0);

        assertEquals(name0, entry.getKey());
        assertEquals(value0, entry.getValue());

        entry = comment.getTags(1);

        assertEquals(name1, entry.getKey());
        assertEquals(value1, entry.getValue());
    }


    protected void testProperty(PropertyInfo property, String name, TypeInfo.Type type) {
        assertEquals(name, property.getName());
        assertEquals(type, property.getTypeInfo().getType());
        assertEquals(0, property.getAnnotations().size());
    }

    protected void testProperty(
            PropertyInfo property, String name, TypeInfo.Type type, String annotationName, List<String> annotationArgs
    ) {
        assertEquals(name, property.getName());
        assertEquals(type, property.getTypeInfo().getType());
        assertEquals(1, property.getAnnotations().size());

        AnnotationInfo annotationInfo = property.getAnnotations().get(0);

        assertType(
                annotationInfo.getTypeInfo(), annotationName
        );
        assertEquals(annotationArgs, annotationInfo.getArgs());
    }
}
