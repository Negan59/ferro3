package bd.dal;

import bd.entidades.Mensagem;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALMensagem {
    public boolean salvar(Mensagem mens) {
        String sql = "insert into mensagem (id_anuncio, doc_usuario, conteudo,data,id_mensagem) values ('$1','$2','$3','$4','$5')";
        sql = sql.replace("$1", ""+mens.getAnuncio().getId());
        sql = sql.replace("$2", mens.getUsuario().getDocumento());
        sql = sql.replace("$3", mens.getConteudo());
        sql = sql.replace("$4", ""+mens.getData());
        sql = sql.replace("$5", ""+mens.getMens().getId());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean salvarPergunta(Mensagem mens){
        String sql = "insert into mensagem (id_anuncio, doc_usuario, conteudo,data) values ('$1','$2','$3','$4')";
        sql = sql.replace("$1", ""+mens.getAnuncio().getId());
        sql = sql.replace("$2", mens.getUsuario().getDocumento());
        sql = sql.replace("$3", mens.getConteudo());
        sql = sql.replace("$4", ""+mens.getData());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean alterar(Mensagem mens) {
        String sql = "update mensagem set id_anuncio = '$1', doc_usuario = '$2', conteudo = '$3', data = '$4', id_mensagem = '$5' where id = " + mens.getId();
        sql = sql.replace("$1", ""+mens.getAnuncio().getId());
        sql = sql.replace("$2", mens.getUsuario().getDocumento());
        sql = sql.replace("$3", mens.getConteudo());
        sql = sql.replace("$4", ""+mens.getData());
        sql = sql.replace("$5", ""+mens.getMens().getId());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
     
    public boolean apagar(int id) {
        Conexao con = new Conexao();
        boolean flag = con.manipular("delete from mensagem where id=" + id);
        con.fecharConexao();
        return flag;
    }
    
    public Mensagem getMensagem(int cod) {
         Mensagem mens = null;
        String sql = "select * from mensagem where id=" + cod;
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                mens = new Mensagem(rs.getInt("id"),new DALAnuncio().getAnuncio(rs.getInt("id_anuncio")),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")), rs.getString("conteudo"),rs.getDate("data").toLocalDate(),new DALMensagem().getMensagem(rs.getInt("id_mensagem")));//problema aqui
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return mens;
    }
    
       public ArrayList<Mensagem> getMensagem(String filtro) {
        ArrayList<Mensagem> lista = new ArrayList();
        String sql = "select * from mensagem";
        if (!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by id";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Mensagem(rs.getInt("id"),new DALAnuncio().getAnuncio(rs.getInt("id_anuncio")),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")), rs.getString("conteudo"),rs.getDate("data").toLocalDate(),new DALMensagem().getMensagem(rs.getInt("id_mensagem"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
}
