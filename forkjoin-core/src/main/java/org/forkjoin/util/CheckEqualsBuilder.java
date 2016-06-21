package org.forkjoin.util;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author zuoge85 on 15/6/19.
 */
public class CheckEqualsBuilder extends EqualsBuilder {

    @Override
    protected void setEquals(final boolean isEquals) {
        super.setEquals(isEquals);
    }
}
