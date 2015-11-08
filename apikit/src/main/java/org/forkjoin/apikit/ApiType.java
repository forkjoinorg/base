package org.forkjoin.apikit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型信息
 *
 * @author zuoge85 on 15/6/12.
 */
public class ApiType {
    private static final Logger log = LoggerFactory.getLogger(ApiType.class);

    private Type type;
    private String packageName;
    private String name;
    private boolean isArray;
    private boolean isApiType = false;
    private List<ApiType> typeArguments = new ArrayList<>();

    private boolean generic = false;


    /**
     * 0. void
     * 1. boolean
     * 2. byte (8位有符号整数)
     * 3. int (32位有符号整数)
     * 4. long (64位有符号整数)
     * 5. float (32位浮点数)
     * 6. double (64位浮点数)
     * 7. String
     * 8. Date
     * 9. enum 枚举类型，只支持简单枚举类型
     * 10. Message类型
     *
     * @author zuoge85
     */
    public enum Type {
        VOID, BOOLEAN, BYTE, INT, LONG, FLOAT,
        DOUBLE, STRING, DATE,
        ENUM, MESSAGE, OTHER;


        public static boolean isHasNull(Type type) {
            return type == STRING || type == DATE ||
                    type == ENUM || type == MESSAGE || type == OTHER;
        }

        public static boolean isBaseType(Type type) {
            return !isHasNull(type);
        }
    }
}
