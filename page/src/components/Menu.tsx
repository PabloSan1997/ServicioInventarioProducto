import { Link } from "react-router-dom"


export function Menu({page}:{page:number}) {
    const retroceder = ()=>{
      if(page>0){
        return `/${page-1}`;
      }
      return `/${page}`;
    }
    const ir = () => `/${page+1}`;
  return (
    <div>
        <Link to={retroceder()}>-</Link>
        <p>{page}</p>
        <Link to={ir()}>+</Link>
    </div>
  );
}
