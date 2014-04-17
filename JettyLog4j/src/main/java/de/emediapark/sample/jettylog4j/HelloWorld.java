package de.emediapark.sample.jettylog4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
 
public class HelloWorld extends AbstractHandler
{
	private static final Logger LOG = Logger.getLogger(HelloWorld.class);
	
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
    	LOG.info("handle enter");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        LOG.debug("response:-start-\n" + response);
        LOG.debug("response:--end--");
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    	LOG.info("handle done");
    }
 
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new HelloWorld());
 
        server.start();
        server.join();
    }
}