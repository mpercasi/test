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

    @Query(value = "Select t.celular from Cliente t where t.usuario=:usuario")
    public String finClienteValidarcelular(String usuario);

    @Query(value = "Select t.usuario from Cliente t where t.correo=:correo")
    public String finClienteValidarcorreo(String correo);

    @Modifying
    @Query(value = "UPDATE clientes SET status =:status WHERE usuario=:usuario and celular=:celular", nativeQuery = true)
    void findDesbloquear(String status,String usuario, String celular);

    @Query(value = "Select t.correo from Cliente t where t.correo=:correo")
    public List<String> findCorreo(String correo);

    @Modifying
    @Query(value = "UPDATE clientes SET contra =:contraN WHERE usuario=:usuario and contra=:contra", nativeQuery = true)
    void actualizarContra(String contraN,String usuario, String contra);

    @Modifying
    @Query(value = "UPDATE clientes SET celular =:celular WHERE usuario=:usuario and contra=:contra", nativeQuery = true)
    void actualizarCelular(String celular,String usuario, String contra);

    @Modifying
    @Query(value = "UPDATE clientes SET correo =:correo WHERE usuario=:usuario and contra=:contra", nativeQuery = true)
    void actualizarCorreo(String correo,String usuario, String contra);

}
