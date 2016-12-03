package com.contabilizei.app;

import com.contabilizei.app.filter.CORSResponseFilter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class App {

    public static void main(String args[]) throws Exception {
        ResourceConfig config = new ResourceConfig();
        config.packages("com.contabilizei.app.controller");
        config.register(CORSResponseFilter.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }

    }

}
