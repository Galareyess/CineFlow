package com.proyecto.gestorcine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

/*Data Transfer Object, Representa los datos de la tarjeta que entran en el sistema */
/**/
public class DatosTarjeta {

    private String numero;
    private String titular;
    private String vencimiento;
    private String cvv;
}
