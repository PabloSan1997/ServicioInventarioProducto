package com.project.list.products.send.serviceproducts.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonedasDto {
    private Float dolar;
    private Float iva;
    private Float porcentajeGanancia;
}
