/* eslint-disable react-hooks/exhaustive-deps */
import { Navigate, useParams } from "react-router-dom";
import { ShowProducto } from "../components/ShowProduct";
import { useAppDispatch, useAppSelector } from "../store/hook";
import { routesIndex } from "../utils/routesIndes";
import React from "react";
import { readApi } from "../api/readApi";



export  function Editar() {
    const state = useAppSelector(state => state.productReducer);
    const dispatch = useAppDispatch();
    const {id} = useParams();
    const num = Number(id);
    React.useEffect(()=>{
        dispatch(readApi.readProductById({token:state.token, id:num}));
    },[num]);
    if(isNaN(num))
        return <Navigate to={routesIndex.home}/>
  return (
    <ShowProducto {...state.editProduct}/>
  );
}
