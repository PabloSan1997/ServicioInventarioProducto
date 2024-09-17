

import { useNavigate } from "react-router-dom";
import { routesIndex } from "../utils/routesIndes";

export function ListaProducto({seccion, linea, serie, marca, descripcion, claveFabricante,clavePCH, garantia, existencias, estado, precioDolar, ucp, iva, precioFinal, precioMXN, ganancia, id}:ProductoMostrar) {
  const goTo = useNavigate();
  return (
    <tr onClick={()=>goTo(`${routesIndex.edit}/${id}`)}>
        <td><p>{seccion}</p></td>
        <td><p>{linea}</p></td>
        <td><p>{serie}</p></td>
        <td><p>{marca}</p></td>
        <td><p>{descripcion}</p></td>
        <td><p>{claveFabricante}</p></td>
        <td><p>{clavePCH}</p></td>
        <td><p>{garantia}</p></td>
        <td><p>{existencias}</p></td>
        <td><p>{estado}</p></td>
        <td><p>{precioDolar}</p></td>
        <td><p>{ucp}</p></td>
        <td><p>{iva}</p></td>
        <td><p>{precioMXN}</p></td>
        <td><p>{precioFinal}</p></td>
        <td><p>{ganancia}</p></td>
    </tr>
  );
}
