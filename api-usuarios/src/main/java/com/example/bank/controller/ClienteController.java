package com.example.bank.controller;

import com.example.bank.entity.Cliente;
import com.example.bank.exception.crudUsuario.*;
import com.example.bank.exception.inicioDeSesion.BlockedUser;
import com.example.bank.exception.inicioDeSesion.NonExistentCustomer2Exception;
import com.example.bank.exception.inicioDeSesion.NonExistentCustomerException;
import com.example.bank.repository.ClienteRepository;
import com.example.bank.service.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    int contador = 0;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteServices clienteServices;

    @GetMapping("MostrarTodosLosclientes")
    public List<Cliente> traerClientes() {
        return clienteRepository.traer();
    }

    @PostMapping("/CrearCliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente)
            throws ClienteExistenteException, MenorDeEdadException,
            TelefonoInvalidoException, CorreoInvalidoException,
            ContraLargoInvalidoException, ContraSinNumeroException, ContraSinAlfaException {
        //return clienteServices.comprobarUsuario(cliente);
        String cli = cliente.getUsuario();
        clienteRepository.obtenerPorUsuario(cli);

        int varTel, varContra;
        String longitudTel = cliente.getCelular();
        String longitudContra = cliente.getContra();
        boolean a = cliente.getCorreo().contains("@");
        varTel = longitudTel.length();
        varContra = longitudContra.length();

        if (cliente.getEdad() >= 18) {

            if (varTel == 10) {

                if (cliente.getCorreo().contains("@")) {

                    if (varContra >= 6) {

                        if (clienteRepository.busquedaNumeros(cliente.getContra()).equals("ok")) {

                            if (clienteRepository.busquedaAlfanumericos(cliente.getContra()).equals("ok")) {

                                clienteRepository.crear(cliente);

                            } else {
                                throw new ContraSinAlfaException();
                            }
                        } else {
                            throw new ContraSinNumeroException();
                        }
                    } else {
                        throw new ContraLargoInvalidoException();
                    }
                } else {
                    throw new CorreoInvalidoException();
                }
            } else {
                throw new TelefonoInvalidoException();
            }
        } else {
            throw new MenorDeEdadException();
        }

        return ResponseEntity.ok(cliente);
    }

    /////Inicio de Sesion*************************************

    @GetMapping("/cliente/{usuarios}/{contras}")
    public ResponseEntity<String> getUsuario(@PathVariable("usuarios") String usuario, @PathVariable("contras") String contra) throws NonExistentCustomerException, NonExistentCustomer2Exception, BlockedUser {
        Optional<Cliente> cliente = clienteServices.getUsuario(usuario, contra);

        String estado = "Bloqueado";
        String estado2 = "Desbloqueado";
        String contrabd = clienteServices.searchContra(usuario);

        Cliente cl = new Cliente();

        if (usuario == null) {
            throw new NonExistentCustomerException();
        }

        if (estado.equals(clienteServices.validarStatus(usuario))) {
            return ResponseEntity.ok("Lo sentimos tu usuario esta bloqueado");
        }

        if (contrabd.equals(contra)) {
            contador = 0;
            return ResponseEntity.ok("Usuario correcto");
        }

        //Valida si la contraseña no es correcta
        if (contra != contrabd) {

            contador = contador + 1;

            if (contador >2) {
                clienteServices.updateStatus(estado, usuario);
            } else
                clienteServices.updateStatus(estado2, usuario);
        }
        throw new NonExistentCustomer2Exception();

    }
}
