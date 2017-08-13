package org.forkjoin.apikit.impl;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.core.Message;
import org.forkjoin.apikit.info.*;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author zuoge85 on 15/12/9.
 */

public class JdtMessageAnalyseTest extends BaseTest {
    @Test
    public void testUser() throws IOException {
        String code = readCode("api/model/User.java");
        Analyse analyse = createAnalyse();
        MessageInfo message = (MessageInfo) analyse.analyse(code, "model");
        assertEquals("User", message.getName());
        assertEquals("model", message.getPackageName());

        ImportsInfo imports = message.getImports();
        assertEquals(4, imports.getImports().size());


        assertImport(imports.get("Message"), Message.class.getName());
        assertImport(imports.get("Length"), Length.class.getName());
        assertImport(imports.get("Min"), Min.class.getName());

        //测试注释
        JavadocInfo comment = message.getComment();
        Map.Entry<String, Collection<List<String>>> entry = comment.getTags(0);

        assertJavadocInfo(message.getComment(), null, Collections.singletonList("用户信息"));

        assertEquals(0, message.getTypeParameters().size());

        //检查属性
        testProperty(message.getProperty("id"), "id", TypeInfo.Type.LONG);
        testProperty(
                message.getProperty("name"),
                "name", TypeInfo.Type.STRING,
                Length.class.getName(),
                Collections.singletonList("max=255")
        );
        assertJavadocInfo(message.getProperty("name").getComment(), null, Collections.singletonList("名称允许重复"));


        testProperty(
                message.getProperty("age"),
                "age", TypeInfo.Type.INT,
                Min.class.getName(),
                Collections.singletonList("14")
        );
    }

    /**
     * 主要测试泛型
     *
     * @throws IOException
     */
    @Test
    public void testTestObject() throws IOException {
        String code = readCode("api/model/TestObject.java");
        Analyse analyse = createAnalyse();
        MessageInfo message = (MessageInfo) analyse.analyse(code, "model");
        assertEquals("TestObject", message.getName());
        assertEquals("model", message.getPackageName());

//        assertEquals(23, message.getProperties().size());

        //泛型参数
        assertEquals(message.getTypeParameters(), Collections.singletonList("T"));

        PropertyInfo generic = message.getProperty("generic");
        assertEquals(generic.getTypeInfo().getFullName(), "T");

        PropertyInfo generics = message.getProperty("generics");

        assertEquals(generics.getTypeInfo().getFullName(), "T");
        assertTrue(generics.getTypeInfo().isArray());
        assertTrue(generics.getTypeInfo().isGeneric());


//        TestObject<T>[] genericObjs;
        PropertyInfo genericObjs = message.getProperty("genericObjs");
        assertTrue(generics.getTypeInfo().isArray());
        TypeInfo typeInfo = genericObjs.getTypeInfo().getTypeArguments().get(0);
        assertEquals(typeInfo.getFullName(), "T");
        assertFalse(typeInfo.isArray());
        assertTrue(typeInfo.isGeneric());
    }
}