package bd.dal;

import bd.entidades.Anuncio;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALAnuncio {
    public boolean salvar(Anuncio a) {
        String sql = "insert into anuncio (conteudo, data_postagem, produto,id_categoria, foto1, foto2, foto3, status, doc_usuario, titulo) values ('$1','$2','$3','$4','$5','$6','$7','$8','$9','$A')";
        sql = sql.replace("$1", a.getConteudo());
        sql = sql.replace("$2", ""+a.getDataPostagem());
        sql = sql.replace("$3", ""+a.getProduto());
        sql = sql.replace("$4", ""+a.getCategoria().getId());
        sql = sql.replace("$5", a.getFoto1());
        sql = sql.replace("$6", a.getFoto2());
        sql = sql.replace("$7", a.getFoto3());
        sql = sql.replace("$8", ""+a.getStatus());
        sql = sql.replace("$9", "" + a.getUsuario().getDocumento());
        sql = sql.replace("$A", "" + a.getTitulo());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean alterar(Anuncio a) {
        String sql = "update anuncio set conteudo = '$1', data_postagem = '$2',produto = '$3',id_categoria = '$4',foto1 = '$5', foto2 = '$6', foto3 = '$7', status = '$8', doc_usuario = '$9', titulo = '$A' where id = " + a.getId();
        sql = sql.replace("$1", a.getConteudo());
        sql = sql.replace("$2", ""+a.getDataPostagem());
        sql = sql.replace("$3", a.getProduto());
        sql = sql.replace("$4", ""+a.getCategoria().getId());
        sql = sql.replace("$5", a.getFoto1());
        sql = sql.replace("$6", a.getFoto2());
        sql = sql.replace("$7", a.getFoto3());
        sql = sql.replace("$8", ""+a.getStatus());
        sql = sql.replace("$9", "" + a.getUsuario().getDocumento());
        sql = sql.replace("$A", "" + a.getTitulo());
        System.out.println(sql);
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean apagar(int id) {
        Conexao con = new Conexao();
        boolean flag = con.manipular("delete from anuncio where id=" + id);
        con.fecharConexao();
        return flag;
    }
    
    public Anuncio getAnuncio(int cod) {
        Anuncio a = null;
        String sql = "select * from anuncio where id=" + cod;
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                a = new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo"));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return a;
    }
    
    public ArrayList<Anuncio> getAnuncioNAprovado(String filtro) {
        ArrayList<Anuncio> lista = new ArrayList();
        String sql = "select * from anuncio where status Like 'R'";
        if (!filtro.isEmpty())
            sql += " and"+ filtro;
        sql += " order by id";
        System.out.println(sql);
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
    
    
    public ArrayList<Anuncio> getAnuncioAprovado(String filtro, int inicio) {
        ArrayList<Anuncio> lista = new ArrayList();
        inicio = (inicio-1)*3;
        String sql = "select * from anuncio where status LIKE 'A' limit "+(inicio + 3)+" offset "+(inicio);
        if (!filtro.isEmpty())
            sql += "and "+filtro ;
        //sql += " order by id";
        System.out.println(sql);
        System.out.println(sql);
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
    
    public ArrayList<Anuncio> getAnuncioUsuario(String doc) {
        ArrayList<Anuncio> lista = new ArrayList();
        String sql = "select * from anuncio where doc_usuario LIKE "+"'"+doc+"'";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
    
    public ArrayList<Anuncio> getAnuncioCategoria(int id) {
        ArrayList<Anuncio> lista = new ArrayList();
        String sql = "select * from anuncio where status LIKE 'A' and id_categoria = "+id;
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
    public ArrayList<Anuncio> get5anuncios() {
        ArrayList<Anuncio> lista = new ArrayList();
        String sql = "SELECT * FROM anuncio where status like 'A' limit 5";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Anuncio(rs.getInt("id"), rs.getString("conteudo"),rs.getDate("data_postagem").toLocalDate(),rs.getString("produto"),new DALCategoria().getCategoria(rs.getInt("id_categoria")),rs.getString("foto1"),rs.getString("foto2"),rs.getString("foto3"),rs.getString("status"),new DALUsuario().getUsuarioUnico(rs.getString("doc_usuario")),rs.getString("titulo")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
    
    public int contarAnuncios() throws SQLException{
        String sql = "SELECT COUNT(*) AS num_item FROM anuncio where status like 'A'";
        Conexao con = new Conexao();
        int aux = 0;
        int teste = 0;
        ResultSet rs = con.consultar(sql);
        while(rs.next()){

              teste = rs.getInt("num_item");
             System.out.println("Total: " + teste);

        }
        /*try {
            if (rs.next())
                 aux = rs;
        } catch (Exception e) {
            System.out.println(e);
        }*/
        con.fecharConexao();
        return teste;
    }
}
