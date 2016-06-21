package org.forkjoin.apikit.generator.jdt;

import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zuoge85 on 15/6/20.
 */
public class JdtUtils {

    /**
     *
     */
    public static String getApiMethodSign(MethodDeclaration method) {

        //@RequestMapping(value = "account/authCode/{mobile}/{type}", method = RequestMethod.GET, produces = "application/json")
        List modifiers = method.modifiers();
        for (Object modifier : modifiers) {
            if (modifier instanceof NormalAnnotation) {
                NormalAnnotation normalAnnotation = (NormalAnnotation) modifier;
                @SuppressWarnings("unchecked")
                List<MemberValuePair> values = normalAnnotation.values();
                if (normalAnnotation.getTypeName().getFullyQualifiedName().equals(RequestMapping.class.getSimpleName())) {
                    String url = null;
                    String httpMethod = null;
                    for (MemberValuePair pair : values) {
                        String pairName = pair.getName().getFullyQualifiedName();
                        String value = pair.getValue().toString();
                        switch (pairName) {
                            case "value":
                                url = value;
                                break;
                            case "method":
                                httpMethod = value;
                                break;
                        }
                    }
                    if (url != null && httpMethod != null) {
                        return url + "$" + httpMethod;
                    }
                }
            }
        }
        return null;
    }
}
