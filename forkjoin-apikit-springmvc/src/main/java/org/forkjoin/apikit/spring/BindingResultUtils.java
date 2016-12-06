package org.forkjoin.apikit.spring;

import org.forkjoin.apikit.core.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author zuoge85 on 15/5/19.
 */
public final class BindingResultUtils {
    public static void add(BindingResult b, String code, Object... args) {
        b.addError(new ObjectError(code, new String[]{code}, args, code));
    }

    public static void addMsg(BindingResult b, String code, String... args) {
        Object[] objs = new Object[args.length];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new DefaultMessageSourceResolvable(args[i]);
        }
        b.addError(new ObjectError(code, new String[]{code}, objs, code));
    }
}
