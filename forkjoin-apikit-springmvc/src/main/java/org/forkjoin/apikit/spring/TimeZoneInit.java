package org.forkjoin.apikit.spring;

import java.util.TimeZone;

/**
 * @author zuoge85 on 15/9/22.
 */
public class TimeZoneInit {
    public TimeZoneInit(String zid){
        TimeZone.setDefault(TimeZone.getTimeZone(zid));
    }
}
