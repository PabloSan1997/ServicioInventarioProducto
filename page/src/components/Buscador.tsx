
import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { productActions } from "../slice/productoSlice";
import '../styles/buscador.scss';
import { Menu } from "./Menu";
import { useParams } from "react-router-dom";

export function Buscador() {
  const dispatch = useAppDispatch();
  const state = useAppSelector(state => state.productReducer);
  const{page} = useParams();
  const num = isNaN(Number(page)) ? 0 : Number(page);
  const escribir = (e:React.ChangeEvent<HTMLInputElement>) =>{
    dispatch(productActions.writeSearch({text:e.target.value.toLocaleUpperCase()}));
  }

  return (
    <form className="buscar_form">
      <input type="text" placeholder="Buscar..." onChange={escribir} value={state.search}/>
      <Menu page={num}/>
    </form>
  );
}
