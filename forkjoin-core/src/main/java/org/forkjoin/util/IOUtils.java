/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forkjoin.util;

import org.forkjoin.core.io.StringAppend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author zuoge85
 */
public class IOUtils {
    
    /**
     * 重写控制台输出
     */
    public static void rewriteSysOut(final StringAppend stringAppend) throws UnsupportedEncodingException{
        final PrintStream out = System.out; 
        System.setOut(new PrintStream(new ByteArrayOutputStream() {
            @Override
            public void flush() throws IOException {
                String str = toString();
                stringAppend.append(str);
                out.append(str);
                reset();
            }
        }, true, "utf8"));
    }
    
    public static void rewriteSysErr(final StringAppend stringAppend) throws UnsupportedEncodingException {
        final PrintStream err = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream() {
            @Override
            public void flush() throws IOException {
                String str = toString();
                stringAppend.append(str);
                err.append(str);
                reset();
            }
        }, true, "utf8"));
    }
}
