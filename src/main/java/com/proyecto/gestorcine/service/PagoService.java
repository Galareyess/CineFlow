package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.dto.DatosTarjeta;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    /*Si todas las condiciones se cumplen, se procesara el pago*/
    public boolean procesarPago(DatosTarjeta tarjeta, BigDecimal monto) {
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

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return true;
    }
}
