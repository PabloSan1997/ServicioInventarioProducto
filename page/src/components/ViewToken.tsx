import { Navigate } from "react-router-dom";
import { useAppSelector } from "../store/hook";
import { routesIndex } from "../utils/routesIndes";


export function ViewToken({ children }: Children) {
    const token = useAppSelector(state => state.productReducer.token);
    if (!token.trim())
        return <Navigate to={routesIndex.login} />
    return (
        <>
            {children}
        </>
    );
}


export function MainRouteToken(){
    const token = useAppSelector(state => state.productReducer.token);
    if (!token.trim())
        return <Navigate to={routesIndex.login} />
    return <Navigate to={routesIndex.home}/>
}