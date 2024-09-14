import { Link } from "react-router-dom"
import { routesIndex } from "../utils/routesIndes";


export function Menu({page}:{page:number}) {
    const retroceder = ()=>{
      if(page>0){
        return `${routesIndex.home}/${page-1}`;
      }
      return `${routesIndex.home}/${page}`;
    }
    const ir = () => `${routesIndex.home}/${page+1}`;
  return (
    <div>
        <Link to={retroceder()}>-</Link>
        <p>{page}</p>
        <Link to={ir()}>+</Link>
    </div>
  );
}
