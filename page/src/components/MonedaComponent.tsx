
import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { readApi } from "../api/readApi";
import { MonedaForm } from "./MonedaForm";
import '../styles/monedaCompnent.scss';

export function MonedaComponent() {
    const [show, setShow] = React.useState(false);
    const state = useAppSelector(state => state.productReducer);
    const moneda = state.moneda;
    const dispatch = useAppDispatch();
    React.useEffect(() => {
        dispatch(readApi.readMoneda({ token: state.token }));
    }, [dispatch, state.token]);
    return (
        <>
            <ul onClick={() => setShow(!show)} className="moneda_component">
                <li>Dolar: ${moneda.dolar}</li>
                <li>Iva: ${moneda.iva}</li>
                <li>Gananciar: ${moneda.porcentajeGanancia}</li>
            </ul>
            {
                show?<MonedaForm onShow={()=>setShow(false)}/>:null
            }
        </>
    );
}
