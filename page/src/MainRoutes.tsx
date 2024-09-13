import { HashRouter, useRoutes } from "react-router-dom";
import { Contenedor } from "./components/Contenedor";



const Rutas = () => useRoutes([
  {
    path: '/:page',
    element: <Contenedor />
  }
]);

export function MainRoutes() {
  return (
    <HashRouter>
      <Rutas />
    </HashRouter>
  );
}
