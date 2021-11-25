package com.example.bank.service;

import com.example.bank.dao.ClienteDao;
import com.example.bank.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    ClienteDao clienteDao;

    public Optional<Cliente> getUsuario(String usuario, String contra) {
        return clienteDao.findClienteValidarUsuario(usuario,contra);
    }

    public Optional <Cliente> searchCliente(String usuario, String contra) {
        return clienteDao.findClienteValidarUsuario(usuario,contra);
    }

    public String searchContra(String usuario) {
        String contra = clienteDao.finClienteValidarcontra(usuario) ;
        return contra;

    }

    public void updateStatus(String estado, String usuario) {
        clienteDao.updateCustomer(estado,usuario);
    }

    public String validarStatus(String usuario) {
        return clienteDao.finClienteValidarstatus(usuario);
    }

}
