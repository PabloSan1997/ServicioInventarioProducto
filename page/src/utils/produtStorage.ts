

export const productStorage = {
    read(){
        if(!localStorage.asdf)
            localStorage.asdf = '';
        return localStorage.asdf;
    },
    save(token:string){
        localStorage.asdf = token;
    }
}