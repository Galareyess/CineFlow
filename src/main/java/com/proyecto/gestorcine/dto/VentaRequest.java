package com.proyecto.gestorcine.dto;

import com.proyecto.gestorcine.entity.Reserva;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class VentaRequest {
    private Reserva reserva;
    private DatosTarjeta tarjeta;
}
