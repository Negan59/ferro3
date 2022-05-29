function geraCabecalho(){
    let header = "";
    let token = localStorage.getItem("token")
    let tipo = ""
     const URL_TO_FETCH='buscatipo?token='+ token;
     header +=   `<div class="collapse navbar-collapse" id="navbarSupportedContent" >
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="index.html">Home</a>
                    </li>`;
     if(token !== null){
        fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
       {
           response.json().then(function(result)  //response é um promisse
           {
               tipo = result
           });
       }).catch (function(err) {console.error(err);});
      
                if(tipo === "admin"){
                   header+= `<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Cadastrar
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="cadastroUsuario.html">Usuarios</a></li>
                            <li><a class="dropdown-item" href="cadastroCategoria.html">Categorias</a></li>
                        </ul>
                    </li>`
                }
                if(tipo === "admin" || tipo === "vendedor"){
                    header+= `<li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="meusAnuncios.html">Meus anúncios</a>
                    </li>`;
                    posta()
                }
                if(tipo === "admin"){
                    header+= `<li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="aprovarAnuncio.html">Aprovar anúncio</a>
                    </li>`
                }
                header+=`
                <form class="d-flex">
                    <input class="form-control me-2" type="text" placeholder="Busca" aria-label="Busca" name="filtro" id="filtro">
                </form>
                <button onclick="carregarAnuncios()" class="btn btn-outline-success">Buscar</button>`
                    
                    
                
    }
    header+=`</ul>
            </div>`;
    document.getElementById('header').innerHTML = header;
}

function posta(){
    let botaopostar = ""
    botaopostar+=`<br>
        <button type="button" class="btn btn-outline-primary"  href="cadastroAnuncio.html"><a href="cadastroAnuncio.html">Novo anúncio</a></button>`;
        document.getElementById('botaopostar').innerHTML = botaopostar;
}


