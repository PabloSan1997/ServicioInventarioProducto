package com.project.list.products.send.serviceproducts.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveProduct {
    private String seccion;
    private String linea;
    private String serie;
    private String marca;
    private String descripcion;
    private String clavePCH;
    private String UCP;
    private String garantia;
    private Long existencias;
    private List<String> urlImages;
    private String estado;
    private Float precioDolar;
}
