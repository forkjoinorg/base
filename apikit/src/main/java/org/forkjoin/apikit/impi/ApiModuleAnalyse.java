package org.forkjoin.apikit.impi;

import org.forkjoin.apikit.AbstractModuleAnalyse;
import org.forkjoin.apikit.info.ModuleType;

/**
 * @author zuoge85 on 15/11/16.
 */
public class ApiModuleAnalyse extends AbstractModuleAnalyse {

    @Override
    public ModuleType getSupportType() {
        return ModuleType.API;
    }
}
