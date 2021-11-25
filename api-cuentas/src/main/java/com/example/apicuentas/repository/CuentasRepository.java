package com.example.apicuentas.repository;

import com.example.apicuentas.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CuentasRepository extends CrudRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM CUENTAS c WHERE c.num_Cliente=:numCliente", nativeQuery = true)
    List<Cuenta> obtenerCuentas(int numCliente);
}
