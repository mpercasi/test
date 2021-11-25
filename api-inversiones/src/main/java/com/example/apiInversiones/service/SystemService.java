package com.example.apiInversiones.service;

import com.example.apiInversiones.entity.DetalleInversion;
import com.example.apiInversiones.entity.Inversion;
import com.example.apiInversiones.entity.PlazoFijo;
import com.example.apiInversiones.entity.TasaInteres;
import com.example.apiInversiones.repository.InversionRepository;
import com.example.apiInversiones.repository.PlazoFijoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SystemService {
    @Autowired
    private InversionRepository inversionRepository;

    @Autowired
    private PlazoFijoRepository plazoFijoRepository;

    public List<PlazoFijo> obtenerPlazos() {
        return plazoFijoRepository.obtenerPlazos();
    }


    @Value("#{'${list.de.tasas.interes}'.split(',')}")
    private List<Double> tasasDisponibles = new ArrayList<>();
    private List<Inversion> opcionesInversiones = new ArrayList<>();

    public List<Inversion> generarPlazos(DetalleInversion detalleInversion) {
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd-MM-yyyy");

        //Crea las fechas de vencimiento de la cotización
        Date cortoPlazo = new Date();
        Date medianoPlazo = new Date();
        Date largoPlazo = new Date();

        cortoPlazo.setMonth(new Date().getMonth() + 3);
        medianoPlazo.setMonth(new Date().getMonth() + 6);
        largoPlazo.setMonth(new Date().getMonth() + 12);

        opcionesInversiones.clear();

        opcionesInversiones.add(new Inversion("Corto", new PlazoFijo(detalleInversion.getMonto(), obtenerDias(cortoPlazo), new TasaInteres(retornarTasaInteres(detalleInversion.getMonto()))), formateadorFecha.format(cortoPlazo)));
        opcionesInversiones.add(new Inversion("Mediano", new PlazoFijo(detalleInversion.getMonto(), obtenerDias(medianoPlazo), new TasaInteres(retornarTasaInteres(detalleInversion.getMonto()))), formateadorFecha.format(medianoPlazo)));
        opcionesInversiones.add(new Inversion("Largo", new PlazoFijo(detalleInversion.getMonto(), obtenerDias(largoPlazo), new TasaInteres(retornarTasaInteres(detalleInversion.getMonto()))), formateadorFecha.format(largoPlazo)));

        return opcionesInversiones;
    }

    private double retornarTasaInteres(double monto){
        if (monto >= 10000 && monto <= 50000){
            return tasasDisponibles.get(0);
        } else if (monto >= 50000 && monto <= 100000){
            return tasasDisponibles.get(1);
        } else if (monto >= 100000){
            return tasasDisponibles.get(2);
        }

        return 0;
    }

    private int obtenerDias(Date fecha){
        int milisecondsByDay = 86400000;
        return (int) ((fecha.getTime() - new Date().getTime()) / milisecondsByDay);
    }

    public Inversion crearInversion(Inversion inversion) {
        inversionRepository.save(inversion);
        return inversion;
    }
}
