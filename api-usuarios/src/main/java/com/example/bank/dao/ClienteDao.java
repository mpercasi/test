package com.example.bank.dao;

import com.example.bank.entity.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClienteDao extends CrudRepository<Cliente, Integer> {

    /*@Query(value = ("Select * From clientes where usuario=:usuario"),nativeQuery = true)
    public List<Cliente> busquedaCliente (String usuario);*/

    @Query(value = "Select t.usuario from Cliente t where t.usuario=:usuario")
    public List<String> findCliente(String usuario);


    // Consultas de inicion de Sesion********************************

    @Query(value = "select * from clientes where usuario=:usuario and contra=:contra", nativeQuery = true)
    public Optional<Cliente> findClienteValidarUsuario(String usuario, String contra);


    @Query(value = "select contra from clientes where usuario =:usuario ", nativeQuery = true)
    public String finClienteValidarcontra(String usuario);

    @Modifying
    @Query(value = "UPDATE clientes SET status =:status WHERE usuario=:usuario ", nativeQuery = true)
    void updateCustomer(String status,String usuario);

    //@Query(value = "select status from clientes where usuario=:usuario ", nativeQuery = true)
    @Query(value = "Select t.status from Cliente t where t.usuario=:usuario")
    public String finClienteValidarstatus(String usuario);

}
