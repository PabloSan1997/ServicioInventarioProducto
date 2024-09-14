/* eslint-disable react-hooks/exhaustive-deps */
import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { readApi } from "../api/readApi";



export function ShowProducto({ seccion, urlImages, linea, serie, marca, descripcion, clavePCH, garantia, existencias, estado, precioDolar, ucp, id }: ProductoMostrar) {
    const initialValue: SaveProducto = {
        seccion, linea, serie, marca, descripcion, clavePCH, garantia, existencias, estado, precioDolar, ucp, urlImages
    };
    const dispatch = useAppDispatch();
    const token = useAppSelector(state => state.productReducer.token);
    const [valores, setValores] = React.useState<string>(JSON.stringify(initialValue));
    React.useEffect(()=>{
        setValores(JSON.stringify(initialValue, null, '\t'));
    },[id>0])
    const subir = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const changes: SaveProducto = JSON.parse(valores);
            dispatch(readApi.putProduct({
                id, producto: changes,token
            }));
        } catch (error) {
            console.error(error);
        }
    }
    const eliminar = () =>{
        if(confirm('¿Seguro que desea elminar producto?')){
            dispatch(readApi.deleteProduct({token, id}))
        }
    }
    return (
        <form onSubmit={subir}>
            <label htmlFor="entradaEditar">Datos</label>
            <textarea id="entradaEditar" value={valores} onChange={e => setValores(e.target.value)}></textarea>
            <button type="submit">Agregar</button>
            <button type="button" onClick={eliminar}>Eliminar</button>
        </form>
    );
}