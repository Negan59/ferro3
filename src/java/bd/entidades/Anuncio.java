
package bd.entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Anuncio {
    private int id;
    private String conteudo;
    private LocalDate dataPostagem;
    private String produto;
    private Categoria categoria;
    private String foto1;
    private String foto2;
    private String foto3;
    private String status;
    private Usuario usuario;

    public Anuncio(int id, String conteudo, LocalDate dataPostagem, String produto, Categoria categoria, String foto1, String foto2, String foto3, String status, Usuario usuario) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataPostagem = dataPostagem;
        this.produto = produto;
        this.categoria = categoria;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.status = status;
        this.usuario = usuario;
    }
    
     public Anuncio(String conteudo, LocalDate dataPostagem, String produto, Categoria categoria, String foto1, String foto2,String foto3, String status, Usuario usuario) {
        this.conteudo = conteudo;
        this.dataPostagem = dataPostagem;
        this.produto = produto;
        this.categoria = categoria;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.status = status;
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

   

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
