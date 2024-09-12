import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { readApi } from "../api/readApi";

const initialState:InitialState = {
    productos: [],
    token: "",
    page:0,
    search:''
}

const productoSlice = createSlice({
    name:'slice/product',
    initialState,
    reducers:{
        logout:(state)=>{
            state.token = '';
            state.productos = [];
            state.page = 0;
        },
        chancgePage:(state, action:PayloadAction<{page:number}>)=>{
            state.page = action.payload.page;
        },
        changeSearch:(state, action:PayloadAction<{search:string}>)=>{
            state.search = action.payload.search;
        }
    },
    extraReducers:(builder)=>{
        builder.addCase(readApi.login.fulfilled, ()=>{});
    }
});


export const productReducer = productoSlice.reducer;
export const productActions = productoSlice.actions;