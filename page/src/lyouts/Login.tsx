import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hook"
import { productActions } from "../slice/productoSlice";
import { readApi } from "../api/readApi";
import { Navigate } from "react-router-dom";
import { routesIndex } from "../utils/routesIndes";
import '../styles/login.scss';


export function Login(){
    const state = useAppSelector(state => state.productReducer);
    const dispatch = useAppDispatch();
    const [loginDto, setLoginDto] = React.useState<LoginDto>({username:'', password:''});
    const subir = (e:React.FormEvent<HTMLFormElement>) =>{
        e.preventDefault();
        const checUSer = !loginDto.username.trim();
        const checPassword = !loginDto.password.trim();
        if(checUSer || checPassword){
            const texto = `${!loginDto.username.trim()?'username':''} ${!loginDto.username.trim()?'password':''} No pueden estar vacios`;
            dispatch(productActions.writeMessage({text:texto}));
        }else{
            dispatch(readApi.login({login:loginDto}));
        }
    }
    if(state.token.trim())
        return <Navigate to={routesIndex.home}/>
    return(
        <form onSubmit={subir} className="form_login">
            <label htmlFor="username">Username</label>
            <input 
            type="text" id="username"
            onChange={e => setLoginDto({...loginDto, username:e.target.value})}
            placeholder="Escribir..."
            />
            <label htmlFor="password">Password</label>
            <input 
            type="password" 
            id="password" 
            onChange={e => setLoginDto({...loginDto, password:e.target.value})}
            placeholder="Escribir..."
            />
            <button className="boton">Entrar</button>
            {!state.message.trim()?null:<p>{state.message}</p>}
        </form>
    )
}