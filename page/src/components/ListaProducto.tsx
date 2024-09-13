

import React from "react";

export function ListaProducto({seccion, linea, serie, marca, descripcion, clavePCH, garantia, existencias, estado, precioDolar, ucp, iva, precioFinal, precioMXN, ganancia}:ProductoMostrar) {
    return (
    <tr>
        <td>{seccion}</td>
        <td>{linea}</td>
        <td>{serie}</td>
        <td>{marca}</td>
        <td>{descripcion}</td>
        <td>{clavePCH}</td>
        <td>{garantia}</td>
        <td>{existencias}</td>
        <td>{estado}</td>
        <td>{precioDolar}</td>
        <td>{ucp}</td>
        <td>{iva}</td>
        <td>{precioMXN}</td>
        <td>{precioFinal}</td>
        <td>{ganancia}</td>
    </tr>
  );
}
