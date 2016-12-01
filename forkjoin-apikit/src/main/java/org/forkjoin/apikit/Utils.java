package org.forkjoin.apikit;

import org.forkjoin.apikit.info.Import;

import java.io.File;

public final class Utils {

    public static File packToPath(String path, String packname, String name, String suffix) {
        File f = new File(path, packname.replace(".", File.separator));
        f = new File(f, name + suffix);
        return f;
    }

    public static File packToPath(String path, String packageName) {
        return new File(path, packageName.replace(".", File.separator));
    }

    /**
     * 去掉对下划线的处理
     */
    public static String toClassName(String str) {
        StringBuilder sb = new StringBuilder();
//        boolean isUp = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (0 == i) {
                sb.append(Character.toUpperCase(c));
            }
//            else if (c == '_') {
//                isUp = true;
//            }
//            else if (isUp) {
//                isUp = false;
//                sb.append(Character.toUpperCase(c));
//            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String pathToPack(String path) {
        return path.replace(File.separator, ".");
    }


    /**
     * 尝试是不是在lang 包内的类
     */
    public static Import getLangImport(String name) {
        try {
            Class<?> aClass = Class.forName("java.lang" + "." + name);
            return new Import("java.lang", name, false, false);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
