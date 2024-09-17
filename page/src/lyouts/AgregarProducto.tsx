import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { readApi } from "../api/readApi";
import '../styles/agregar_formulario.scss';

const initialData: SaveProducto = {
    seccion: "",
    linea: "",
    serie: "",
    marca: "",
    descripcion: "",
    claveFabricante: "",
    clavePCH: "",
    garantia: "",
    existencias: 0,
    urlImages: [],
    estado: "",
    precioDolar: 0,
    ucp: ""
}

export function AgregarProducto() {
    const [datos, setDatos] = React.useState<string>(JSON.stringify([initialData], null, '\t'));
    const dispatch = useAppDispatch();
    const state = useAppSelector(state => state.productReducer);
    const submit = (e:React.FormEvent<HTMLFormElement>) =>{
        e.preventDefault();
        try {
            const producto:SaveProducto[] = JSON.parse(datos);
            producto[0].descripcion = producto[0].descripcion.toLocaleUpperCase();
            dispatch(readApi.save({token:state.token, producto}));
        } catch (error) {
            console.error(error);
        }
    }
    return (
        <form onSubmit={submit} className="agregar_formulario editar_formulario">
            <label htmlFor="nuevoProducto">Datos</label>
            <textarea name="" id="nuevoProducto" value={datos} onChange={e => setDatos(e.target.value)}></textarea>
            <button type="submit" className="boton">Agregar</button>
        </form>
    );
}
