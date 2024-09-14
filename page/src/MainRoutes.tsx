import { HashRouter, Navigate, useRoutes } from "react-router-dom";
import { MainRouteToken, ViewToken } from "./components/ViewToken";
import { routesIndex } from "./utils/routesIndes";
import { Home } from "./lyouts/Home";
import { Login } from "./lyouts/Login";
import { Editar } from "./lyouts/Editar";



const Rutas = () => useRoutes([
  {
    path: '/',
    element: <MainRouteToken />
  },
  {
    path: routesIndex.home,
    element:<Navigate to={`${routesIndex.home}/0`}/>
  },
  {
    path: routesIndex.edit,
    element: <Navigate to={`${routesIndex.home}/0`} />
  },
  {
    path: `${routesIndex.edit}/:id`,
    element: (
      <ViewToken>
        <Editar />
      </ViewToken>
    )
  },
  {
    path: `${routesIndex.home}/:page`,
    element: (
      <ViewToken>
        <Home />
      </ViewToken>
    )
  },
  {
    path: routesIndex.login,
    element: <Login />
  }
]);

export function MainRoutes() {
  return (
    <HashRouter>
      <Rutas />
    </HashRouter>
  );
}
