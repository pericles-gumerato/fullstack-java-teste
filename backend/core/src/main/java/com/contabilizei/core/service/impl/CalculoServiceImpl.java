package com.contabilizei.core.service.impl;

import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesResponse;
import com.contabilizei.core.service.CalculoService;
import com.contabilizei.model.entity.Cliente;

import java.util.Calendar;
import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class CalculoServiceImpl implements CalculoService {

    private final ClienteDao clienteDao;
    private final ImpostoMesDao impostoMesDao;

    public CalculoServiceImpl(ClienteDao clienteDao, ImpostoMesDao impostoMesDao) {
        this.clienteDao = clienteDao;
        this.impostoMesDao = impostoMesDao;
    }

    @Override
    public CalcularImpostosMesResponse calcularImpostosMes(CalcularImpostosMesRequest request) {
        CalcularImpostosMesResponse response = new CalcularImpostosMesResponse();

        Long clienteId = request.getClienteId();
        Calendar mesAnoReferencia = Calendar.getInstance();
        int mes = request.getMes();
        mesAnoReferencia.set(Calendar.MONTH, mes > 0 ? mes - 1 : mes); // Adjusting human month number -> Java month number
        mesAnoReferencia.set(Calendar.YEAR, request.getAno());

        Optional<Cliente> cliente = clienteDao.buscarCliente(clienteId);
        if (!cliente.isPresent()) {
            response.setSucesso(false);
            response.setMensagem("Cliente não encontrado");

            return response;
        }

        response.setSucesso(true);

        return response;
    }

}
