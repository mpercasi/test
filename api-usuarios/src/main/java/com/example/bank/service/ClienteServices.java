package com.example.bank.service;

import com.example.bank.dao.ClienteDao;
import com.example.bank.entity.Cliente;
import com.example.bank.exception.inicioDeSesion.ContraOUsuarioInvalido;
import com.example.bank.exception.inicioDeSesion.NonExistentCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    ClienteDao clienteDao;

    public Optional<Cliente> getUsuario(String usuario, String contra) throws NonExistentCustomerException {
            return clienteDao.findClienteValidarUsuario(usuario,contra);
    }

    public Optional <Cliente> searchCliente(String usuario, String contra) {
        return clienteDao.findClienteValidarUsuario(usuario,contra);
    }

    public String searchContra(String usuario) throws ContraOUsuarioInvalido {
        String correo = clienteDao.finClienteValidarcontra(usuario);
        if(!correo.isEmpty()){

            return clienteDao.finClienteValidarcontra(usuario);
        }
        throw new ContraOUsuarioInvalido();

    }

    public String searchStatus(String usuario) {
        String status = clienteDao.finClienteValidarstatus(usuario) ;
        return status;
    }

    public void updateStatus(String estado, String usuario) {
        clienteDao.updateCustomer(estado,usuario);
    }

    public String validarStatus(String usuario) {
        return clienteDao.finClienteValidarstatus(usuario);
    }

    public String searchCelular(String usuario) {
        return clienteDao.finClienteValidarcelular(usuario);
    }

    public String searchUsuarioPorCorreo(String correo) {
        return clienteDao.finClienteValidarcorreo(correo);
    }

    public void desbloquear(String estado, String usuario, String celular){
        clienteDao.findDesbloquear(estado, usuario, celular);
    }

    public void cambiarContra(String contraN, String usuario, String contra){
        clienteDao.actualizarContra(contraN, usuario, contra);
    }

    public void cambiarCelular(String celular, String usuario, String contra){
        clienteDao.actualizarCelular(celular, usuario, contra);
    }

    public void cambiarCorreo(String correo, String usuario, String contra){
        clienteDao.actualizarCorreo(correo, usuario, contra);
    }

}
