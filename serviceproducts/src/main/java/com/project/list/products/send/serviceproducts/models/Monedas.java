package com.project.list.products.send.serviceproducts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "monedas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monedas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float dolar;
    private Float iva;
    @Column(name = "porcentaje_ganancia")
    private Float porcentajeGanancia;

    @OneToMany(mappedBy = "monedas")
    @JsonIgnore
    private List<Producto> productos;

}
