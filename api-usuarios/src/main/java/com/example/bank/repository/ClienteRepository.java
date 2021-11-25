package com.example.bank.repository;

import com.example.bank.dao.ClienteDao;
import com.example.bank.entity.Cliente;
import com.example.bank.exception.crudUsuario.ClienteExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {
    private List<Cliente> listaClientes;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ClienteRepositoryDao clienteRepositoryDao;

    public void obtenerPorUsuario(String usuario) throws ClienteExistenteException {
        System.out.println(clienteDao.findCliente(usuario));
        if(!clienteDao.findCliente((usuario)).isEmpty()){
            throw new ClienteExistenteException();
        }
    }

    public List<Cliente> traer(){
        return (List<Cliente>) clienteDao.findAll();
    }

    public Cliente crear(Cliente cliente){
        clienteDao.save(cliente);
        return cliente;
    }

    public String busquedaNumeros(String contra){
        char[] contraChar = contra.toCharArray();
        char[] numero = {'1','2','3','4','5','6','7','8','9','0'};
        for(int i = 0; i<numero.length;i++){
            for (int j=0; j<contraChar.length;j++){
                if (numero[i]==contraChar[j]){
                    return "ok";
                }
            }
        }
        return "";
    }

    public String busquedaAlfanumericos(String contra){
        char[] contraChar = contra.toCharArray();
        char[] alfa = {'!','"','#','$','%','&','/','(',')','=','?','¿','¡','*','+','}','{','_','-',':','^','@'};
        for(int i = 0; i<alfa.length;i++){
            for (int j=0; j<contraChar.length;j++){
                if (alfa[i]==contraChar[j]){
                    return "ok";
                }
            }
        }
        return "";
    }
}
