import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { readApi } from "../api/readApi";

type MonedaFromProps = {
    onShow(): void
}




export function MonedaForm({ onShow }: MonedaFromProps) {
    const state = useAppSelector(state => state.productReducer);
    const dispatch = useAppDispatch();
    const { dolar, iva, porcentajeGanancia } = state.moneda;
    const [formulario, setFormulario] = React.useState<SaveMoneda>({ dolar, iva, porcentajeGanancia });
    const submit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        dispatch(readApi.editMoneda({ token: state.token, moneda: formulario }));
    }
    return (
        <form onSubmit={submit} className="moneda_form">
            <label htmlFor="dolar">Dolar</label>
            <input
                type="number"
                id="dolar"
                value={formulario.dolar}
                onChange={e => setFormulario({ ...formulario, dolar: Number(e.target.value) })}
            />
            <label htmlFor="iva">Iva</label>
            <input
                type="number"
                id='iva'
                value={formulario.iva}
                onChange={e => setFormulario({ ...formulario, iva: Number(e.target.value) })}
            />
            <label htmlFor="ganancia">Ganancia</label>
            <input
                type="number"
                id='ganancia'
                value={formulario.porcentajeGanancia}
                onChange={e => setFormulario({ ...formulario, porcentajeGanancia: Number(e.target.value) })}
            />
            <div className="area_botones">
                <button type='submit' className="boton">Cambiar</button>
                <button type="button" onClick={onShow} className="boton">Cancelar</button>
            </div>
        </form>
    );
}
