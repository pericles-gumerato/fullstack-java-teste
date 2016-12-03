package com.contabilizei.app.util;

import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.dao.NotaFiscalDao;
import com.contabilizei.core.dao.impl.ClienteDaoImpl;
import com.contabilizei.core.dao.impl.ImpostoMesDaoImpl;
import com.contabilizei.core.dao.impl.NotaFiscalDaoImpl;
import com.contabilizei.core.service.CadastroService;
import com.contabilizei.core.service.CalculoService;
import com.contabilizei.core.service.ConsultaService;
import com.contabilizei.core.service.UpdateService;
import com.contabilizei.core.service.impl.CadastroServiceImpl;
import com.contabilizei.core.service.impl.CalculoServiceImpl;
import com.contabilizei.core.service.impl.ConsultaServiceImpl;
import com.contabilizei.core.service.impl.UpdateServiceImpl;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ServicesManager {

    private final CadastroService cadastroService;
    private final ConsultaService consultaService;
    private final UpdateService updateService;
    private final CalculoService calculoService;

    /**
     * This is a simple services manager to avoid creating multiple instances of the same service.
     * It also instantiates single DAOs instances.
     */
    public ServicesManager() {
        // initializing DAOs
        ClienteDao clienteDao = new ClienteDaoImpl();
        ImpostoMesDao impostoMesDao = new ImpostoMesDaoImpl();
        NotaFiscalDao notaFiscalDao = new NotaFiscalDaoImpl();

        // initializign services
        this.cadastroService = new CadastroServiceImpl(clienteDao, notaFiscalDao);
        this.consultaService = new ConsultaServiceImpl(clienteDao, impostoMesDao, notaFiscalDao);
        this.updateService = new UpdateServiceImpl(impostoMesDao);
        this.calculoService = new CalculoServiceImpl(clienteDao, impostoMesDao, notaFiscalDao);
    }

    public CadastroService getCadastroService() {
        return cadastroService;
    }

    public ConsultaService getConsultaService() {
        return consultaService;
    }

    public UpdateService getUpdateService() {
        return updateService;
    }

    public CalculoService getCalculoService() {
        return calculoService;
    }
}
