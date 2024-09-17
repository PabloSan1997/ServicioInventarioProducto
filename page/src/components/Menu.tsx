import { Link } from "react-router-dom"
import { routesIndex } from "../utils/routesIndes";
import '../styles/menu.scss';

export function Menu({page}:{page:number}) {
    const retroceder = ()=>{
      if(page>0){
        return `${routesIndex.home}/${page-1}`;
      }
      return `${routesIndex.home}/${page}`;
    }
    const ir = () => `${routesIndex.home}/${page+1}`;
  return (
    <div className="menu_seccion">
        <Link to={retroceder()} className="menu_boton">-</Link>
        <p>{page}</p>
        <Link to={ir()} className="menu_boton">+</Link>
    </div>
  );
}
