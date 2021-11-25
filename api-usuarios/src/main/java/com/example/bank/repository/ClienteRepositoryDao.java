package com.example.bank.repository;

import com.example.bank.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ClienteRepositoryDao extends CrudRepository<Cliente, Integer> {

    /*@Query(value = "Select t.usuario from Cliente t where t.usuario=:usuario")
    public List<String> findCliente(String usuario);*/
}
