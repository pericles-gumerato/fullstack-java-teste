package com.contabilizei.app;

import com.contabilizei.app.filter.CORSResponseFilter;
import com.contabilizei.app.util.ServicesManagerFactory;
import com.contabilizei.core.requestsandresponses.BuscarClientesRequest;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String args[]) throws Exception {
        ResourceConfig config = new ResourceConfig();

        logger.info("Starting database connection");
        startDatabaseConnection();
        logger.info("Database connection started");

        config.packages("com.contabilizei.app.controller");
        config.register(CORSResponseFilter.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        DynamicIntProperty serverPort =
                DynamicPropertyFactory.getInstance().getIntProperty("server.port", 8080);
        Server server = new Server(serverPort.getValue());
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        logger.info("Server ready");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }

    }

    private static void startDatabaseConnection() {
        BuscarClientesRequest req = new BuscarClientesRequest();
        req.setMaxPorPagina(1);
        req.setPagina(1);
        ServicesManagerFactory.getServicesManager().getConsultaService().consultaTodosClientes(req);
    }

}
