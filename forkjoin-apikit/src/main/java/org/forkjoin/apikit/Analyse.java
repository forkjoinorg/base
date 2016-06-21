package org.forkjoin.apikit;


import org.forkjoin.apikit.info.ModuleInfo;

/**
 * 代码分析器
 * @author zuoge85 on 15/11/8.
 */
public interface Analyse {
    ModuleInfo analyse(String code, String pack);
}
