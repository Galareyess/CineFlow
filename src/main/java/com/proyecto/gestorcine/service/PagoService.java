package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.dto.DatosTarjeta;
import org.springframework.stereotype.Service;

/*Pago simulado*/
@Service
public class PagoService {

    /*Si todas las condiciones se cumplen, se procesara el pago*/
    public boolean procesarPago(DatosTarjeta tarjeta, int monto) {
        if (tarjeta == null) {
            return false;
        }

        if (tarjeta.getNumero() == null || tarjeta.getNumero().length() != 16) {
            return false;
        }

        if(tarjeta.getTitular() == null || tarjeta.getTitular().isBlank()){
            return false;
        }

        if(tarjeta.getCvv() == null || tarjeta.getCvv().length() != 3) {
            return false;
        }

        if(tarjeta.getVencimiento() == null || tarjeta.getVencimiento().isBlank()) {
            return false;
        }

        if(monto <= 0){
            return false;
        }

        if(monto > tarjeta.getFondos()){
            return false;
        }
        tarjeta.setFondos(tarjeta.getFondos() - monto);

        return true;
    }
}
