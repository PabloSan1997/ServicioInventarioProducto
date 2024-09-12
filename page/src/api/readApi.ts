import { createAsyncThunk } from "@reduxjs/toolkit";


export const readApi = {
    urlBase: import.meta.env.DEV ? 'http://localhost:3005' : window.location.origin,
    readProducts: createAsyncThunk(
        'extraReducer/readproducts',
        async ({ token, page }: { token: string, page: number }): Promise<ProductoMostrar[]> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product?size=10&page=${page}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    readBySearch: createAsyncThunk(
        'extraReducer/readproductsSearch',
        async ({ token, page, name }: { token: string, page: number, name: string }): Promise<ProductoMostrar[]> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product/search?size=10&page=${page}&name=${name}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    readByPrice: createAsyncThunk(
        'extraReducer/readproductsOrder',
        async ({ token, page, name, orderby }: { token: string, page: number, name: string, orderby: 'asc' | 'desc' }): Promise<ProductoMostrar[]> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product/price-${orderby}?size=10&page=${page}&name=${name}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    save: createAsyncThunk(
        'extraReducer/saveProduct',
        async ({ token, producto }: { token: string, producto: SaveProducto[] }): Promise<ProductoMostrar> => {
            const lista:ListSaveProducts = {
                products:producto
            }
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    },
                    body: JSON.stringify(lista)
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    putProduct: createAsyncThunk(
        'extraReducer/putProduct',
        async ({ token, id, producto }: { token: string, id: number, producto: SaveProducto }): Promise<ProductoMostrar> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    },
                    body: JSON.stringify(producto)
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    deleteProduct: createAsyncThunk(
        'extraReducer/deleteProduct',
        async ({ token, id }: { token: string, id: number }): Promise<{ id: number }> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/product/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + token
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return { id };

            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    ),
    login: createAsyncThunk(
        'extraReducer/login',
        async ({ login }: { login: LoginDto }): Promise<LoginResponse> => {
            try {
                const ft = await fetch(`${readApi.urlBase}/api/user/login`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(login)
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const { message } = error as { message: string }
                throw { message };
            }
        }
    )
}