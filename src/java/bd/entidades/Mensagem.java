package bd.entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Mensagem {
    private int id;
    private Anuncio anuncio;
    private Usuario usuario;
    private String conteudo;
    private LocalDate data;
    private Mensagem mens;
    
    public Mensagem(){
        this(0,null,null,"",null,null);
    }

    public Mensagem(Anuncio anuncio, Usuario usuario, String conteudo, LocalDate data) {
        this.anuncio = anuncio;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.data = data;
    }

    public Mensagem(int id, Anuncio anuncio, Usuario usuario, String conteudo, LocalDate data, Mensagem mens) {
        this.id = id;
        this.anuncio = anuncio;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.data = data;
        this.mens = mens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mensagem getMens() {
        return mens;
    }

    public void setMens(Mensagem mens) {
        this.mens = mens;
    }

    public Mensagem(Anuncio anuncio, Usuario usuario, String conteudo, LocalDate data, Mensagem mens) {
        this.anuncio = anuncio;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.data = data;
        this.mens = mens;
    }

    
    

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }


    
    
    
}
