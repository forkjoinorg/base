package org.forkjoin.apikit;


import org.forkjoin.apikit.info.ModuleInfo;

/**
 * @author zuoge85 on 15/11/8.
 */
public interface Analyse {
    ModuleInfo analyse(String code, String pack);
    void setObjectFactory(ObjectFactory objectFactory);
}
