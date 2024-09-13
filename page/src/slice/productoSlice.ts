import { createSlice} from "@reduxjs/toolkit";
import { readApi } from "../api/readApi";

const initialState:InitialState = {
    productos: [],
    token: "eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn0se1wiYXV0aG9yaXR5XCI6XCJST0xFX1VTRVJcIn1dIiwic3ViIjoicGFibG8xMjMiLCJpYXQiOjE3MjYxOTIxMDQsImV4cCI6MTcyNjI3ODUwNH0.mc2kvnAnRsNiSn4J_CV4jr5sGHtW7sFhOFou4JEyOK4-9d3-qVj0XXXGnvay6TiSPRltYqMQ5dRQhOgYb7EDGA",
    search:''
}

const productoSlice = createSlice({
    name:'slice/product',
    initialState,
    reducers:{
        logout:(state)=>{
            state.token = '';
            state.productos = [];
        }
    },
    extraReducers:(builder)=>{
        builder.addCase(readApi.login.fulfilled, ()=>{});

        builder.addCase(readApi.readProducts.fulfilled, (state, action)=>{
            state.productos = action.payload;
        })
    }

});


export const productReducer = productoSlice.reducer;
export const productActions = productoSlice.actions;