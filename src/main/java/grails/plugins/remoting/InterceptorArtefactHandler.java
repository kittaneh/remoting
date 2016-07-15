
package grails.plugins.remoting;

import grails.core.ArtefactHandlerAdapter;
import grails.core.InjectableGrailsClass;
import org.grails.core.AbstractInjectableGrailsClass;

/**
 * 
 */
public final class InterceptorArtefactHandler extends ArtefactHandlerAdapter {

    public static final String TYPE = "RemotingInterceptor";

    public InterceptorArtefactHandler() {
        super(TYPE, InterceptorGrailsClass.class, DefaultInterceptorGrailsClass.class, null);
    }

    @Override
    public boolean isArtefactClass(@SuppressWarnings("rawtypes") Class clazz) {
        return clazz != null && clazz.getName().endsWith(TYPE);
    }

    public static interface InterceptorGrailsClass extends InjectableGrailsClass {
        // no extra methods
    }

    public static class DefaultInterceptorGrailsClass extends AbstractInjectableGrailsClass implements InterceptorGrailsClass {
        public DefaultInterceptorGrailsClass(Class<?> wrappedClass) {
            super(wrappedClass, InterceptorArtefactHandler.TYPE);
        }
    }
}
