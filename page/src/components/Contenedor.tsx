/* eslint-disable react-hooks/exhaustive-deps */


import React from "react";
import { Menu } from "./Menu";
import { cabezas } from "../utils/encabezados";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { readApi } from "../api/readApi";
import { useParams } from "react-router-dom";
import { ListaProducto } from "./ListaProducto";



export  function Contenedor() {
    const dispatch = useAppDispatch();
    const state = useAppSelector(state => state.productReducer);
    const {page} = useParams();
    const num = isNaN(Number(page))?0:Number(page);
    React.useEffect(()=>{
        dispatch(readApi.readProducts({token:state.token, page:num}));
    },[page]);
  return(
    <div>
        <Menu page={num}/>
        <table className="data">
            <thead>
                <tr>
                    {cabezas.map(p => 
                        <th key={p}>{p}</th>
                    )}
                </tr>
            </thead>
            <tbody>
                {state.productos.map(p =>(
                    <ListaProducto key={p.id} {...p}/>
                ))}
            </tbody>
        </table>
    </div>
  );
}
