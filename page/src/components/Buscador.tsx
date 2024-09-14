
import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { productActions } from "../slice/productoSlice";

export function Buscador() {
  const dispatch = useAppDispatch();
  const state = useAppSelector(state => state.productReducer);
  const escribir = (e:React.ChangeEvent<HTMLInputElement>) =>{
    dispatch(productActions.writeSearch({text:e.target.value}));
  }
  return (
    <form>
      <input type="text" placeholder="Buscar..." onChange={escribir} value={state.search}/>
    </form>
  );
}
