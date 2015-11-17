package org.forkjoin.apikit;

import org.eclipse.jdt.core.dom.QualifiedName;
import org.forkjoin.apikit.info.ImportsInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85 on 15/11/15.
 */
public abstract class AbstractAnalyse implements Analyse{
    protected String name;
    protected String packageName;
    protected Map<String, QualifiedName> nameMaps = new HashMap<>();

    protected ImportsInfo importsInfo = new ImportsInfo();
}
