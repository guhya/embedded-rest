package gframework;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedServer {
	
	public static void main(String  args[]) throws Exception{
	    Server server = new Server(9090);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        
        server.setHandler(context);
 
        server.start();
        server.join();

	}

}
