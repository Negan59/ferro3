function geraCabecalho(){
    let header = "";
    header +=   `<ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="index.html">Home</a>
                    </li>`;
    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Cadastrar
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="cadastroUsuario.html">Usuarios</a></li>
                            <li><a class="dropdown-item" href="cadastroCategoria.html">Categorias</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="meusAnuncios.html">Meus anúncios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="aprovarAnuncio.html">Aprovar anúncio</a>
                    </li>
                    
                    
                </ul>`;
}


