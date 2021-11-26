package com.example.bank.controller;

import com.example.bank.entity.Cliente;
import com.example.bank.exception.crudUsuario.CorreoInexistenteException;
import com.example.bank.exception.crudUsuario.SinCelularException;
import com.example.bank.exception.crudUsuario.*;
import com.example.bank.exception.inicioDeSesion.BlockedUser;
import com.example.bank.exception.inicioDeSesion.ContraOUsuarioInvalido;
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

    //Crear Usuarios-------------------------------------
    @PostMapping("/CrearCliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) throws ClienteExistenteException, MenorDeEdadException, TelefonoInvalidoException, CorreoInvalidoException, ContraLargoInvalidoException, ContraSinNumeroException, ContraSinAlfaException, CorreoYaExistenteException {
        clienteRepository.obtenerPorUsuario(cliente.getUsuario());
        clienteRepository.obtenerPorCorreo(cliente.getCorreo());

        int varTel, varContra;
        String longitudTel = cliente.getCelular();
        String longitudContra = cliente.getContra();
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
    @GetMapping("/iniciosesion/{usuarios}/{contras}")
    public ResponseEntity<String> getUsuario(@PathVariable("usuarios") String usuario, @PathVariable("contras") String contra) throws NonExistentCustomerException, NonExistentCustomer2Exception, BlockedUser, ContraOUsuarioInvalido {
        //Optional<Cliente> cliente = clienteServices.getUsuario(usuario, contra);

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
            return ResponseEntity.ok("Inicio de sesion correcto");
        }

        //Valida si la contraseña no es correcta
        if (contra != contrabd) {

            contador = contador + 1;

            if (contador > 2) {
                clienteServices.updateStatus(estado, usuario);
            } else
                clienteServices.updateStatus(estado2, usuario);
        }
        throw new NonExistentCustomer2Exception();
    }

    //Desbloquear Usuario----------------------------------
    @GetMapping("/desbloquear/{usuario}/{celular}")
    public ResponseEntity<String> desbloquear(@PathVariable("usuario") String usuario, @PathVariable("celular") String celular) throws SinCelularException {
        String celularbd = clienteServices.searchCelular(usuario);
        if (celularbd.equals(celular)) {
            clienteServices.desbloquear("Desbloqueado", usuario, celular);
            String statusbd = clienteServices.searchStatus(usuario);

            if (statusbd.equals("Desbloqueado")) {

                return ResponseEntity.ok("Desbloqueado");
            } else {
                return ResponseEntity.ok("Bloqueado");
            }
        }
        throw new SinCelularException();
    }

    //Recuperar usuario------------------------------------
    @GetMapping("/recuperar/{correo}")
    public ResponseEntity<String> recuperarUsuario(@PathVariable String correo) throws CorreoInexistenteException {
        //String correobd = clienteServices.searchUsuarioPorCorreo(correo);
        String correobd = clienteServices.searchUsuarioPorCorreo(correo);
        if (!correobd.isEmpty()) {
            return ResponseEntity.ok(clienteServices.searchUsuarioPorCorreo(correo));
        } else {
            throw new CorreoInexistenteException();
        }
    }

    //Cambiar contraseña-----------------------------------
    @PutMapping("/cambiarcontra/{usuario}/{contra}")
    public ResponseEntity<Cliente> modificarcontra(@PathVariable("usuario") String usuario, @PathVariable("contra") String contra, @RequestBody Cliente cliente) throws ContraSinAlfaException, ContraSinNumeroException, ContraLargoInvalidoException, ContraOUsuarioInvalido, NonExistentCustomer2Exception {

        String contrabd = clienteServices.searchContra(usuario);
        int varTel, varContra;
        String longitudContra = cliente.getContra();
        varContra = longitudContra.length();

        if (contrabd.equals(contra)) {
            if (varContra >= 6) {

                if (clienteRepository.busquedaNumeros(cliente.getContra()).equals("ok")) {

                    if (clienteRepository.busquedaAlfanumericos(cliente.getContra()).equals("ok")) {

                        clienteServices.cambiarContra(cliente.getContra(), usuario, contra);
                        return ResponseEntity.ok(cliente);

                    } else {
                        throw new ContraSinAlfaException();
                    }
                } else {
                    throw new ContraSinNumeroException();
                }
            } else {
                throw new ContraLargoInvalidoException();
            }
        }
        throw new NonExistentCustomer2Exception();

    }

    //Cambiar Celular--------------------------------------
    @PutMapping("/cambiarcelular/{usuario}/{contra}")
    public ResponseEntity<Cliente> modificarcelular(@PathVariable("usuario") String usuario, @PathVariable("contra") String contra, @RequestBody Cliente cliente) throws ContraOUsuarioInvalido, TelefonoInvalidoException, NonExistentCustomer2Exception {

        String contrabd = clienteServices.searchContra(usuario);
        int varTel;
        String longitudTel = cliente.getCelular();
        varTel = longitudTel.length();
        if (contrabd.equals(contra)) {
            if (varTel == 10) {

                clienteServices.cambiarCelular(cliente.getCelular(), usuario, contra);
                return ResponseEntity.ok(cliente);

            } else {
                throw new TelefonoInvalidoException();
            }
        }
        throw new NonExistentCustomer2Exception();
    }

    //Cambiar Email----------------------------------------
    @PutMapping("/cambiaremail/{usuario}/{contra}")
    public ResponseEntity<Cliente> modificarcorreo(@PathVariable("usuario") String usuario, @PathVariable("contra") String contra, @RequestBody Cliente cliente) throws ContraSinAlfaException, ContraSinNumeroException, ContraLargoInvalidoException, ContraOUsuarioInvalido, NonExistentCustomer2Exception, CorreoInvalidoException {

        String contrabd = clienteServices.searchContra(usuario);

        if (contrabd.equals(contra)) {

            if (cliente.getCorreo().contains("@")) {

                clienteServices.cambiarCorreo(cliente.getCorreo(), usuario, contra);
                return ResponseEntity.ok(cliente);

            } else {
                throw new CorreoInvalidoException();
            }
        }
        throw new NonExistentCustomer2Exception();

    }
}
