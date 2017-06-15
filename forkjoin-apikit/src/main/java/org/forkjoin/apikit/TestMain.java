package org.forkjoin.apikit;

import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.info.TypeInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author zuoge85@gmail.com on 2017/6/13.
 */
public class TestMain {
    public static void main(String[] args) throws NoSuchMethodException {
        int[] aaa;
        System.out.println(int.class.isPrimitive());
        Class<int[]> aClass = int[].class;
        System.out.println(aClass.getComponentType());
        System.out.println(Integer[].class.isPrimitive());
//        Class<PageResult> cls = PageResult.class;
//        System.out.println(cls);
//        Method getData = cls.getMethod("getData");
//        System.out.println(getData);
//        System.out.println(getData);
//        System.out.println(getData.getReturnType());
//        Class<?> returnType = getData.getReturnType();
//        Type genericReturnType = getData.getGenericReturnType();
//        System.out.println(genericReturnType);
//
//        Type genericSuperclass = getResultGenericSuperclass(cls);
//        TypeInfo typeInfo = TypeInfo.form(genericReturnType);
//        Class<? super PageResult> superclass = cls.getSuperclass();
//        System.out.println(superclass);
//        System.out.println(genericSuperclass);
    }

    private static Type getResultGenericSuperclass(Class<?> cls){
        Class<?> superclass = cls.getSuperclass();
        if(superclass.equals(Result.class)){
            Type genericSuperclass = cls.getGenericSuperclass();
            return genericSuperclass;
        }else {
            return getResultGenericSuperclass(superclass);
        }
    }
}
