package com.contabilizei.app.util;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ServicesManagerFactory {

    private static ServicesManager servicesManager = null;

    public static ServicesManager getServicesManager() {
        if (servicesManager == null) {
            servicesManager = new ServicesManager();
        }

        return servicesManager;
    }

}
