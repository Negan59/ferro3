async function carregarAnuncios(inicio){
    var verifica = document.getElementById("filtro");
    let URL_TO_FETCH = ""
    
    if(localStorage.getItem("token")!==null){
        paginacao()
        var filtro=document.getElementById("filtro").value; // verifica o filtro
        if(inicio === undefined){
            inicio = 1;
        }
        URL_TO_FETCH='consultaranuncio?filtro='+filtro+'&inicio='+inicio+'&token='+localStorage.getItem("token");
    }
    else{
        URL_TO_FETCH = 'consultar5anuncios';
        document.getElementById('carregaBotao').innerHTML = "";
        document.getElementById('paginacao').innerHTML = "";
    }
    console.log(URL_TO_FETCH)
    await fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let monta="";
            let i = 0;
            console.log(result[0])
            for (let anuncio of result)
            {
                console.log(anuncio);
                monta+=`<div class="card mx-auto" style="width:40rem;border-width: 8px;border-color: #00BFFF">
                <div style="text-align: center;font-size: 48px">${anuncio.titulo}</div>
                        <div style="text-align: center;font-size: 25px">${anuncio.conteudo}</div>
                        
                        <div style="text-align: center;font-size: 25px;color: blue">${anuncio.produto}</div>`;
                        
                monta+=`<div style="text-align: center;font-size: 25px;color: blue">Vendedor : ${anuncio.usuario.nome}</div>
        <div id="carouselExampleIndicators_${i}" class="carousel slide" data-bs-ride="true" style="max-width: 40rem;">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
        <div class="carousel-inner">
            <div class="carousel-item active" style="max-height:30rem">
                <img src="${anuncio.foto1}" class="d-block w-100" alt="${anuncio.foto1}">
            </div>
            <div class="carousel-item" style="max-height:30rem">
                <img src="${anuncio.foto2}" class="d-block w-100" alt="${anuncio.foto2}">
            </div>
            <div class="carousel-item" style="max-height:30rem">
                <img src="${anuncio.foto3}" class="d-block w-100" alt="${anuncio.foto3}">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div></div><br>
                
        `;
            i++;
            }
            document.getElementById('anuncio').innerHTML = monta;
        });
    }).catch (function(err) {console.error(err);});
    
}

async function gravaAnuncio(){
    const URL_TO_FETCH = 'gravaranuncio?token='+localStorage.getItem("token");
    console.log(URL_TO_FETCH)
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fanuncios'))) {
        data.append(pair[0], pair[1]);
    }
    console.log(data);
    await fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        console.log(retorno)
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('fanuncios').reset();            
        }
        alert("para")
         
    }).catch(function (error) {
        console.error(error);
    });
}

async function MostraCategorias()
{   
    var filtro=""; // verifica o filtro
    const URL_TO_FETCH='consultarcategoria?filtro='+filtro+'&token='+localStorage.getItem("token");
       
    await fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let tbody="";
            for (let categoria of result)
            {
                tbody+=`<option value="${categoria.id}">${categoria.nome}</option>`;
            }
            document.getElementById('categoria').innerHTML = tbody;
        });
    }).catch (function(err) {console.error(err);});
}

async function anuncioCategoria(id){
     const URL_TO_FETCH='consultaranunciocategoria?id='+id+'&token='+localStorage.getItem("token");
     await fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let monta="";
            let i = 0;
            for (let anuncio of result)
            {
                console.log(anuncio);
                monta+=`<div class="card mx-auto" style="width:40rem;border-width: 8px;border-color: #00BFFF">
                        <div style="text-align: center;font-size: 48px">${anuncio.titulo}</div>
                        <div style="text-align: center;font-size: 25px">${anuncio.conteudo}</div>
                        <div style="text-align: center;font-size: 20px">${anuncio.dataPostagem.day}/${anuncio.dataPostagem.month}/${anuncio.dataPostagem.year}</div>
                        <div style="text-align: center;font-size: 25px;color: blue">${anuncio.produto}</div>`;
                        
                monta+=`<div style="text-align: center;font-size: 25px;color: blue">Vendedor : ${anuncio.usuario.nome}</div>
        <div id="carouselExampleIndicators_${i}" class="carousel slide" data-bs-ride="true" style="max-width: 40rem;">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active" style="max-height:30rem">
                    <img src="${anuncio.foto1}" class="d-block w-100" alt="${anuncio.foto1}">
                </div>
                <div class="carousel-item" style="max-height:30rem">
                    <img src="${anuncio.foto2}" class="d-block w-100" alt="${anuncio.foto2}">
                </div>
                <div class="carousel-item" style="max-height:30rem">
                    <img src="${anuncio.foto3}" class="d-block w-100" alt="${anuncio.foto3}">
                </div>
            </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_${i}" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
        </div>
    <div style="text-align: center;font-size: 48px">${anuncio.titulo}</div>
    <div class="container">
                Teste
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="cadastroUsuario.html">Usuarios</a></li>
            <li><a class="dropdown-item" href="cadastroCategoria.html">Categorias</a></li>
        </ul>
    </div>
    </div><br>
                
        `;
            i++;
            }
            document.getElementById('anuncio').innerHTML = monta;
        });
    }).catch (function(err) {console.error(err);});
}

async function Categoria()
{   
    var filtro=""; // verifica o filtro
    const URL_TO_FETCH='consultarcategoria?filtro='+filtro+'&token='+localStorage.getItem("token");
    if(localStorage.getItem("token")!==null){
    await fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let tbody="";
            for (let categoria of result)
            {
                tbody+=`<li><a class="dropdown-item" onclick="anuncioCategoria(${categoria.id})">${categoria.nome}</a></li>`;
                
            }
            document.getElementById('mcategoria').innerHTML = tbody;
        });
    }).catch (function(err) {console.error(err);});
    }else{
        document.getElementById('mcategoria').innerHTML = "";
    }
}

async function paginacao(){
    const URL_TO_FETCH='contaranuncios';
    let anuncios = 0
    let pagina = ""
    await fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            console.log(result)
            localStorage.setItem("resultado",result);
        });
    }).catch (function(err) {console.error(err);});
    anuncios = localStorage.getItem("resultado")
    pagina+=`<ul class="pagination">`
     let qtnd = Math.round(anuncios%3)
     for(let i = 1;i<=qtnd+1;i++){
         pagina+=`<li class="page-item"><a class="page-link" onclick="carregarAnuncios(${i})">${i}</a></li>`;
     }
    
    pagina+=`</ul>`
    document.getElementById('paginacao').innerHTML = pagina;
}
