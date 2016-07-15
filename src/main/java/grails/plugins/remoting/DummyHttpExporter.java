
package grails.plugins.remoting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.HttpRequestHandler;

/**
 * A Spring remote exporter that can be used in place of any HTTP
 * based exporter. Its role is to replace existing exporters,
 * thereby "switching" them off. This is because you can not simply
 * remove an exporter from the Spring context at the moment.
 * @author Peter Ledbrook
 */
public final class DummyHttpExporter extends RemoteExporter implements HttpRequestHandler {
	/**
	 * Always returns a 404 HTTP status.
	 */
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}
