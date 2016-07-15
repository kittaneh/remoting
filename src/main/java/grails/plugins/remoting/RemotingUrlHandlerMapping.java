
package grails.plugins.remoting;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

@SuppressWarnings("rawtypes")
public class RemotingUrlHandlerMapping extends AbstractUrlHandlerMapping {

    private static final Pattern INVOKER_URL_PATTERN = Pattern.compile("/(\\w+)/(\\w+)");

    private Set invokerTypes;

    public RemotingUrlHandlerMapping() {
        setAlwaysUseFullPath(true);
    }

    public void setInvokerTypes(Set types) {
        invokerTypes = types;
    }

    @Override
    protected Object lookupHandler(String urlPath, HttpServletRequest request) {
        Matcher m = INVOKER_URL_PATTERN.matcher(urlPath);
        if (m.matches()) {
            // Extract the invoker type...
            String invokerType = m.group(1);
            if (invokerTypes.contains(invokerType)) {
                return invokerType + '.' + m.group(2);
            }
        }

        // No matching invoker found.
        return null;
    }
}