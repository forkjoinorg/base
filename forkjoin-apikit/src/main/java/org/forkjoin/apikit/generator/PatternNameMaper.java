package org.forkjoin.apikit.generator;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternNameMaper implements NameMaper{

    /**
     * A RegExp Pattern that extract needed information from a service ID. Ex :
     * "(?<name>.*)-(?<version>v.*$)"
     */
    private Pattern sourcePattern;
    /**
     * A RegExp that refer to named groups define in servicePattern. Ex :
     * "${version}/${name}"
     */
    private String distPattern;


    public PatternNameMaper(String sourcePattern, String distPattern) {
        this.sourcePattern = Pattern.compile(sourcePattern);
        this.distPattern = distPattern;
    }

    /**
     *
     */
    public String apply(String name) {
        Matcher matcher = this.sourcePattern.matcher(name);
        String distName = matcher.replaceFirst(this.distPattern);
        distName = clean(distName);
        return (StringUtils.hasText(distName) ? distName : name);
    }

    private String clean(final String route) {
        String routeToClean = route.replaceAll("/{2,}", "/");
        if (routeToClean.startsWith("/")) {
            routeToClean = routeToClean.substring(1);
        }
        if (routeToClean.endsWith("/")) {
            routeToClean = routeToClean.substring(0, routeToClean.length() - 1);
        }
        return routeToClean;
    }
}
