
package bd.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Usuario {
    
    private String documento;
    private String genero, estado, nome, tipo_usuario, endereco;
    private LocalDate datanascimento;
    private String senha;
    private String email;

    

    /*public Usuario(){
        this(0, "", "", "", "", "", LocalDate.now());
    }*/

    public Usuario(String documento, String genero, String estado, String nome, String tipo_usuario, String endereco, LocalDate datanascimento, String senha, String email) {
        this.documento = documento;
        this.genero = genero;
        this.estado = estado;
        this.nome = nome;
        this.tipo_usuario = tipo_usuario;
        this.endereco = endereco;
        this.datanascimento = datanascimento;
        this.senha = senha;
        this.email = email;
    }
    
    public LocalDate getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(LocalDate datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return datanascimento;
    }

    public void setDataNascimento(LocalDate datanascimento) {
        this.datanascimento = datanascimento;
    }
    
    
    
}
