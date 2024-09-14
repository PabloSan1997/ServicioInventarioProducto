import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { readApi } from "../api/readApi";
import { productStorage } from "../utils/produtStorage";
import { routesIndex } from "../utils/routesIndes";

const initialState: InitialState = {
    productos: [],
    token: productStorage.read(),
    search: '',
    message:'',
    editProduct:{
        seccion: "",
        linea: "",
        serie: "",
        marca: "",
        descripcion: "",
        clavePCH: "",
        garantia: "",
        existencias: 0,
        urlImages: [],
        estado: "",
        precioDolar: 0,
        ucp: "",
        id: 0,
        ganancia: 0,
        precioMXN: 0,
        precioFinal: 0,
        iva: 0,
        claveFabricante: ""
    },
    moneda:{
        id: 1,
        dolar: 0,
        iva: 0,
        porcentajeGanancia: 0
    }
}

const productoSlice = createSlice({
    name: 'slice/product',
    initialState,
    reducers: {
        logout: (state) => {
            state.token = '';
            state.productos = [];
        },
        writeSearch(state, action: PayloadAction<{ text: string }>) {
            state.search = action.payload.text;
        },
        writeMessage(state, action:PayloadAction<{text:string}>){
            state.message = action.payload.text;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(readApi.login.fulfilled, (state, action) => {
            state.token = action.payload.token;
            productStorage.save(action.payload.token);
            state.message = '';
        });
        builder.addCase(readApi.login.rejected, (state, action)=>{
            state.message = action.error.message as string;
        })

        builder.addCase(readApi.readProducts.fulfilled, (state, action) => {
            state.productos = action.payload;
            state.editProduct.id = 0;
        });
        builder.addCase(readApi.readProducts.rejected, (state) => {
            state.token = '';
            productStorage.save('');
        });
        builder.addCase(readApi.readBySearch.fulfilled, (state, action) => {
            state.productos = action.payload;
            state.editProduct.id = 0;
        });
        builder.addCase(readApi.readBySearch.rejected, (state) => {
            state.token = '';
            productStorage.save('');
        });

        builder.addCase(readApi.readProductById.fulfilled, (state, action)=>{
            state.editProduct = action.payload;
        });
        builder.addCase(readApi.putProduct.fulfilled, (state)=>{
            state.editProduct = initialState.editProduct;
            window.location.href = `/#${routesIndex.home}`;
        });
        builder.addCase(readApi.save.fulfilled, ()=>{
            window.location.href = `/#${routesIndex.home}`;
        });
        builder.addCase(readApi.readMoneda.fulfilled, (state, action)=>{
            state.moneda = action.payload;
        });
        builder.addCase(readApi.editMoneda.fulfilled, (state, action)=>{
            state.moneda = action.payload;
        });

        builder.addCase(readApi.deleteProduct.fulfilled, ()=>{
            window.location.href = `/#${routesIndex.home}`;
        });
    }

});


export const productReducer = productoSlice.reducer;
export const productActions = productoSlice.actions;