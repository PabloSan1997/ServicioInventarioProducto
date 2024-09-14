import { Link, useLocation } from "react-router-dom";
import { routesIndex } from "../utils/routesIndes";
import { MonedaComponent } from "./MonedaComponent";




export function Header() {
  const location = useLocation();
  const viewEdit = (`/${location.pathname.split('/')[1]}`)
  return (
    <header>
      <h1>Lista</h1>
      {
        location.pathname == routesIndex.newProduct || viewEdit == routesIndex.edit ?
          <Link to={routesIndex.home}>Home</Link> :
          <Link to={routesIndex.newProduct}>Agregar</Link>
      }
      <MonedaComponent />
    </header>
  );
}
