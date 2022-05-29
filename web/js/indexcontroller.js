function autenticar() {
    const URL_TO_FETCH = 'Autenticar';
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    fetch(URL_TO_FETCH, {
        method: 'post',
        body: data})
            .then(function (response) {
                return response.text();
            })
            .then(function (text) { 
                alert(text);
                localStorage.setItem("token", text);
               
            })
            .catch(function (error) {
                console.error(error);
            });


}

function listarcategorias()
{
    let token = localStorage.getItem("token"); //pegar o token
    const URL_TO_FETCH = 'testarAcesso';
    fetch(URL_TO_FETCH, {method: 'POST', headers: {'Authorization': `${token}`, }})
            .then(function (response)
            {
                response.text()
                        .then(function (result)
                        {
                            alert(result);
                        });
            })
            .catch(function (err)
            {
                console.error(err);
            });

}

function MostraUsuarios()
{   
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='consultarusuario?filtro='+filtro+'&token='+localStorage.getItem("token");
       
    fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let tbody="";
            console.log(result)
            for (let usuario of result)
            {
                tbody+=`<tr><td>${usuario.documento}</td><td>${usuario.email}</td><td>${usuario.nome}</td><td>${usuario.genero}</td><td>${usuario.tipo_usuario}</td><td>${usuario.datanascimento.day}/${usuario.datanascimento.month}/${usuario.datanascimento.year}</td><td>${usuario.endereco}</td><td>${usuario.estado}</td>
                        <td onclick='AlteraUsuario(${usuario.documento})'><img src='icones/alterar.png'/></td>
                        <td onclick='ApagarUsuario(${usuario.documento})'><img src='icones/apagar.png'/></td></tr>`;
            }
            document.getElementById('preview').innerHTML = tbody;
        });
    }).catch (function(err) {console.error(err);});
}

function GravaUsuario()
{
    event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'gravarusuario?token = '+localStorage.getItem("token");
    
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fusuarios'))) {
        data.append(pair[0], pair[1]);
    }
    console.log(data);
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        console.log(retorno)
        window.alert("para")
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('fusuarios').reset();            
            MostraUsuarios();
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}

function AlteraUsuario(documento)
{   
    let url = "buscarusuario?documento=" + documento+'&token='+localStorage.getItem("token");
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
           {
               console.log(result)
                 console.log(result.erro)
                if (result.erro===undefined)
                {
                  
                  var form = document.forms["fusuarios"];
                  form.documento.value = result.documento;
                  form.nome.value = result.nome;
                  form.genero.value = result.genero;
                  form.tipo_usuario.value = result.tipo_usuario;
                  form.datanascimento.value = ""+result.datanascimento.year+"-"+result.datanascimento.month+"-"+result.datanascimento.day;
                  form.endereco.value = result.endereco;
                  form.estado.value = result.estado;
                }
                else
                {
                    //alert(result.mens);
                    document.getElementById('erro').innerHTML = result.mens;
                }
            }
        });
    }).catch (function(err) {console.error(err);});
}

function ApagarUsuario(documento)
{   console.log(documento);
    let url = "apagarusuario?documento=" + documento+'&token='+localStorage.getItem("token");
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
      response.json().then(function(result)  //response é um promisse
      {
          console.log(result)
          if (result.tipo==="Erro")
          {
              //alert(result.mens);
              document.getElementById('erro').style.display = 'block';
              document.getElementById('erromsg').innerHTML = result.mens;
          }
          else
              MostraUsuarios();
      });
    }).catch (function(err) {console.error(err);});
}

function MostraCategorias()
{   
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='consultarcategoria?filtro='+filtro+'&token='+localStorage.getItem("token");
       
    fetch(URL_TO_FETCH, {method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
            //let resultjson=JSON.parse(result);
            // result contém a resposta do módulo dinâmico
            let tbody="";
            for (let categoria of result)
            {
                tbody+=`<tr><td>${categoria.id}</td><td>${categoria.nome}</td>
                        <td onclick='AlterarCategoria(${categoria.id})'><img src='icones/alterar.png'/></td>
                        <td onclick='ApagarCategoria(${categoria.id})'><img src='icones/apagar.png'/></td></tr>`;
            }
            document.getElementById('preview').innerHTML = tbody;
        });
    }).catch (function(err) {console.error(err);});
}

function GravaCategoria()
{
    //event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'incluircategoria?token='+localStorage.getItem("token");
    
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fcategorias'))) {
        data.append(pair[0], pair[1]);
    }
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
            document.getElementById('fcategorias').reset();            
            MostraCategorias();
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}

function ApagarCategoria(id)
{   
    let url = "apagarcategoria?id=" + id+'&token='+localStorage.getItem("token");
    console.log(url)
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
      response.json().then(function(result)  //response é um promisse
      {
          if (result.tipo==="Erro")
          {
              //alert(result.mens);
              document.getElementById('erro').style.display = 'block';
              document.getElementById('erromsg').innerHTML = result.mens;
          }
          else
              MostraCategorias();
      });
    }).catch (function(err) {console.error(err);});
}

function AlterarCategoria(id)
{   
    let url = "buscarcategoria?id=" + id+'&token='+localStorage.getItem("token");
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.json().then(function(result)  //response é um promisse
        {
           {
                if (result.erro===undefined)
                {
                    console.log(result)
                  var form = document.forms["categorias"];
                  form.id.value = result.id;
                  form.nome.value = result.nome;
                }
                else
                {
                    //alert(result.mens);
                    document.getElementById('erro').innerHTML = result.mens;
                }
            }
        });
    }).catch (function(err) {console.error(err);});
}
