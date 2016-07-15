package grails.plugins.remoting;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raed Zidat
 * Date: 12/02/12
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHttpInvokerServiceExporter extends HttpInvokerServiceExporter {

    private Object proxy;

    @Override
    public void prepare() {
    this.proxy = getProxyForService();
}


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String esbApproved="";
        try {
//            PkiEncryption pkiEncryption =new PkiEncryption();
//            try{
//            PkiEncryption.generateKeys();
//            }catch (Exception e1){
//            }

            RemoteInvocation invocation = readRemoteInvocation(request);
            if (invocation.getAttributes() != null) {
                Map attributes = invocation.getAttributes();

//                try{
//                 esbApproved = PkiEncryption.decrypt((byte[]) attributes.get("esbApproved"));
//                }catch (Exception e1){
//                }

                esbApproved = (String)attributes.get("esbApproved") ;
                String consumer=(String)attributes.get("Consumer");
                if(consumer==null ||esbApproved==null){
                    throw new NestedServletException("Class not found during deserialization");
                } else
                {
                    if (esbApproved.equalsIgnoreCase("yes")){
                        RemoteInvocationResult result = invokeAndCreateResult(invocation, this.proxy);
                        writeRemoteInvocationResult(request, response, result);
                    }
                }
            }
        }
        catch (ClassNotFoundException ex) {
            throw new NestedServletException("Class not found during deserialization", ex);
        }
    }
    }