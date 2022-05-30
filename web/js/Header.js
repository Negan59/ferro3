function geraCabecalho(){
    let header = "";
    let tipo = ""
    
     if(localStorage.getItem("token") !== null){
          header +=   `<div class="collapse navbar-collapse" id="navbarSupportedContent" >
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="index.html">Home</a>
                    </li>`;
        
                if(localStorage.getItem("tipo") === "admin"){
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
                if(localStorage.getItem("tipo") === "vendedor"){
                    header+= `<li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="meusAnuncios.html">Meus anúncios</a>
                    </li>`;
                    
                }
                if(window.location.href === "index.html" && localStorage.getItem("tipo") === "vendedor"){
                    posta()
                }
                if(localStorage.getItem("tipo") === "admin"){
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

function logout(){
    window.alert("delogado");
    localStorage.clear();
}

function carregarLogin(){
    let login = ""
    
    if(localStorage.getItem("token") === null){
        login+=`<div class="login-userimg" >
                        <a href="login.html">
                        <img src="https://i.ibb.co/RDNW9qJ/icons8-usu-rio-50.png" alt="Foto de usuário" />
                        Faça Login
                        </a>
                    </div>`
    }
    else{
        
       login+=`<div class="login-userimg" >
                        <a onclick="logout()" href="login.html">
                        <img src="https://i.ibb.co/RDNW9qJ/icons8-usu-rio-50.png" alt="Foto de usuário" />
                        ${localStorage.getItem("nome")}
                        </a>
                    </div>`
        
    }
    document.getElementById('login').innerHTML = login;
}


