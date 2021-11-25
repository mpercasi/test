package com.example.apiInversiones.repository;

import com.example.apiInversiones.entity.PlazoFijo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlazoFijoRepository extends CrudRepository<PlazoFijo, Integer> {

    @Query(value = "SELECT * FROM PLAZOS", nativeQuery = true)
    List<PlazoFijo> obtenerPlazos();
}
