package com.project.list.products.send.serviceproducts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seccion;
    private String linea;
    private String serie;
    private String marca;
    private String descripcion;
    private String clavePCH;
    private String UCP;
    private String garantia;
    private Long existencias;
    @ElementCollection
    @CollectionTable(name = "url_images", joinColumns = @JoinColumn(name = "id_user"))
    private List<String> urlImages;
    private String estado;
    private Float precioDolar;
    @ManyToOne
    @JoinColumn(name = "id_moneda")
    @JsonIgnore
    private Monedas monedas;

    public Float getPrecioMXN(){
        return precioDolar* monedas.getDolar();
    }
    public Float getIva(){
        return getPrecioMXN()*monedas.getIva();
    }
    public Float getPrecioFinal(){return getIva()*monedas.getPorcentajeGanancia();}
    public Float getGanancia(){return getIva()*(monedas.getPorcentajeGanancia()-1);}

}
