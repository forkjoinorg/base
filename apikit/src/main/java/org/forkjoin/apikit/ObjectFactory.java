package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ModuleType;

/**
 * @author zuoge85 on 15/11/15.
 */
public interface ObjectFactory {
    Analyse createAnalyse();
    ModuleAnalyse createModuleAnalyse(ModuleType type);
}
