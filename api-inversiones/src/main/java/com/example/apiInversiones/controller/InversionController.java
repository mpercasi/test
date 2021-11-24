package com.example.apiInversiones.controller;

import com.example.apiInversiones.entity.DetalleInversion;
import com.example.apiInversiones.entity.Inversion;
import com.example.apiInversiones.entity.PlazoFijo;
import com.example.apiInversiones.exceptions.DuplicatedException;
import com.example.apiInversiones.exceptions.NonExistentException;
import com.example.apiInversiones.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inversiones")
@CrossOrigin(origins = {"*"})
public class InversionController {

    @Autowired
    private SystemService system;

    @GetMapping("/plazos")
    public List<PlazoFijo> getPlazos() throws NonExistentException {
        List<PlazoFijo> plazos = system.obtenerPlazos();
        if (plazos.isEmpty()){
            throw new NonExistentException();
        }
        return plazos;
    }

    @PostMapping
    public ResponseEntity<List<Inversion>> generarPlazos(@RequestBody DetalleInversion detalleInversion) throws DuplicatedException {

        if(detalleInversion.equals(null) || detalleInversion.equals("")){
            throw new DuplicatedException();
        }
        return ResponseEntity.ok(system.generarPlazos(detalleInversion));
    }

    @PostMapping("crearInversion/{numCliente}")
    public ResponseEntity<Inversion> crearInversion(@PathVariable int numCliente, @RequestBody Inversion inversion) throws NonExistentException {

        //Falta buscar al cliente

        return ResponseEntity.ok(system.crearInversion(inversion));
    }
}
