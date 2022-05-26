
package bd.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import bd.entidades.Categoria;
import bd.util.Conexao;

public class DALCategoria {
    public boolean salvar(Categoria c) {
        String sql = "insert into categoria (nome) values ('$1')";
        sql = sql.replace("$1", c.getNome());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }

    public boolean alterar(Categoria c) {
        String sql = "update categoria set nome = '$1' where id = " + c.getId();
        sql = sql.replace("$1", c.getNome());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }

    public boolean apagar(int id) {
        Conexao con = new Conexao();
        boolean flag = con.manipular("delete from categoria where id=" + id);
        con.fecharConexao();
        return flag;
    }

    public Categoria getCategoria(int cod) {
        Categoria c = null;
        String sql = "select * from categoria where id=" + cod;
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                c = new Categoria(rs.getInt("id"), rs.getString("nome"));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return c;
    }

    public ArrayList<Categoria> getCategoria(String filtro) {
        ArrayList<Categoria> lista = new ArrayList();
        String sql = "select * from categoria";
        if (!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by nome";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Categoria(rs.getInt("id"), rs.getString("nome")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
}
