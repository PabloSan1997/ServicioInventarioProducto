/// <reference types="vite/client" />


interface ProductoMostrar {
    id: number;
    seccion: string;
    linea: string;
    serie: string;
    marca: string;
    descripcion: string;
    clavePCH: string;
    garantia: string;
    existencias: number;
    urlImages: string[];
    estado: string;
    precioDolar: number;
    ucp: string;
    ganancia: number;
    precioMXN: number;
    precioFinal: number;
    iva: number;
}

interface SaveProducto {
    seccion: string;
    linea: string;
    serie: string;
    marca: string;
    descripcion: string;
    clavePCH: string;
    garantia: string;
    existencias: number;
    urlImages: string[];
    estado: string;
    precioDolar: number;
    ucp: string;
}

interface ListSaveProducts {
    products: SaveProducto[]
}

interface LoginDto {
    username: string;
    password: string;
}
interface LoginResponse {
    username: string;
    token: string;
}

interface InitialState {
    productos: ProductoMostrar[];
    token: string;
    search: string;
    message: string;
    editProduct:ProductoMostrar;
}

interface Children {
    children: JSX.Element | JSX.Element[]
}

