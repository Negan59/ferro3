function MostraAnuncios()
{   
    const URL_TO_FETCH='listaranuncio?doc_usuario='+"'46339689809'";
       
    fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let tbody="";
            console.log(result)
            for (let anuncio of result)
            {
                tbody+=`<tr><td>${anuncio.conteudo}</td><td>${anuncio.produto}</td><td>${anuncio.categoria.nome}</td><td>${anuncio.usuario.nome}</td>
                        <td onclick='fotosEnvia(${anuncio.id})'><img src='icones/alterar.png'/></td></tr>`;
            }
            document.getElementById('preview').innerHTML = tbody;
        });
    }).catch (function(err) {console.error(err);});
}

function fotosEnvia(id){
    let formulario = "";
    formulario+=`<p class="form-group form-group-lg">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="foto1" name="foto1" enctype="multipart/form-data">
                        <label class="custom-file-label" for="customFileLang">Foto 1</label>
                    </div>
                </p>`;
    formulario+=`<p class="form-group form-group-lg">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="foto2" name="foto2" enctype="multipart/form-data">
                        <label class="custom-file-label" for="customFileLang">Foto 2</label>
                    </div>
                </p>`;
    formulario+=`<p class="form-group form-group-lg">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="foto3" name="foto3" enctype="multipart/form-data">
                        <label class="custom-file-label" for="customFileLang">Foto 3</label>
                    </div>
                </p>`;
    formulario+=`<p>
                        <button onclick="envia(${id})" class="form-control  mb-2 mr-sm-2 btn btn-primary">Cadastrar</button>
                    </p>`;
    document.getElementById('fotos').innerHTML = formulario;
                    
}

function envia(id){
    const URL_TO_FETCH = 'gravararquivo?id='+id;
    
    /*const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fotos'))) {
        data.append(pair[0], pair[1]);
    }*/
    var data = new FormData(document.getElementById('fotos'));
    console.log(data)
    window.alert(data);
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {          
            MostraAnuncios();
        }
         
    }).catch(function (error) {
        console.error(error);
    });
}


