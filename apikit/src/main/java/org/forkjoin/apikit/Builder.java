package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;

/**
 * @author zuoge85 on 15/11/8.
 */
public interface Builder {
    void build(MessageInfo m);

    void build(ApiInfo m);
}
