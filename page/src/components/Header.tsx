import { Link, useLocation } from "react-router-dom";
import { routesIndex } from "../utils/routesIndes";
import { MonedaComponent } from "./MonedaComponent";
import { useAppSelector } from "../store/hook";
import '../styles/header.scss';


export function Header() {
  const location = useLocation();
  const viewEdit = (`/${location.pathname.split('/')[1]}`)
  const token = useAppSelector(state => state.productReducer.token);
  if (!token.trim())
    return null;
  return (
    <header>
      <div className="superior">
        <h1>Lista</h1>
        {
          location.pathname == routesIndex.newProduct || viewEdit == routesIndex.edit ?
            <Link to={routesIndex.home} className="ir">Home</Link> :
            <Link to={routesIndex.newProduct} className="ir">Agregar</Link>
        }
      </div>
      <MonedaComponent />
    </header>
  );
}
