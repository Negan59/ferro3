
package bd.dal;

import bd.entidades.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALUsuario {
    
    //int documento, String genero, String id_estado, String nome, String tipo_usuario, String endereco, LocalTime dataNascimento
    public boolean salvar(Usuario u) {
        String sql = "insert into usuario (documento, genero, estado, nome, tipo_usuario, endereco, datanascimento,senha,email) values ('$1','$2','$3','$4','$5','$6','$7','$8','$9')";
        sql = sql.replace("$1", u.getDocumento()); //usar id? insere documento aqui?
        sql = sql.replace("$2", u.getGenero());
        sql = sql.replace("$3", u.getEstado()); //??? id estado?
        sql = sql.replace("$4", u.getNome());
        sql = sql.replace("$5", u.getTipo_usuario());
        sql = sql.replace("$6", u.getEndereco());
        sql = sql.replace("$7", "" + u.getDataNascimento());
        sql = sql.replace("$8",u.getSenha());
        sql = sql.replace("$9",u.getEmail());
        System.out.println(sql);
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean alterar(Usuario u) {
        String sql = "update usuario set genero = '$1', estado = '$2', nome = '$3',tipo_usuario = '$4',endereco = '$5', datanascimento = '$6',senha = '$7', email = '$8 where documento like " + u.getDocumento();
        sql = sql.replace("$1", u.getGenero());
        sql = sql.replace("$2", u.getEstado());
        sql = sql.replace("$3", u.getNome());
        sql = sql.replace("$4", u.getTipo_usuario());
        sql = sql.replace("$5", u.getEndereco());
        sql = sql.replace("$6", "" + u.getDataNascimento());
        sql = sql.replace("$7",u.getSenha());
        sql = sql.replace("$8",u.getEmail());
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean alteraE(String email, String senha) {
        String sql = "update usuario set senha = '$1' where email like " + email;
        sql = sql.replace("$1", senha);
        Conexao con = new Conexao();
        boolean flag = con.manipular(sql);
        con.fecharConexao();
        return flag;
    }
    
    public boolean apagar(String cod){
        Conexao con = new Conexao();
        System.out.println(cod);
        boolean flag = con.manipular("delete from usuario where documento like '" + cod + "'");
        con.fecharConexao();
        return flag;
    }
    public Usuario getUsuarioUnico(String cod) {
        Usuario u = null;
        String sql = "select * from usuario where documento like '" + cod +"'";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                u = new Usuario(rs.getString("documento"), rs.getString("genero"), rs.getString("estado"), rs.getString("nome"), rs.getString("tipo_usuario"), rs.getString("endereco"), rs.getDate("datanascimento").toLocalDate(),rs.getString("senha"),rs.getString("email"));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return u;
    }
    
    public Usuario getUsuarioUnicoE(String cod) {
        Usuario u = null;
        String sql = "select * from usuario where email like '" + cod +"'";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                u = new Usuario(rs.getString("documento"), rs.getString("genero"), rs.getString("estado"), rs.getString("nome"), rs.getString("tipo_usuario"), rs.getString("endereco"), rs.getDate("datanascimento").toLocalDate(),rs.getString("senha"),rs.getString("email"));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return u;
    }
    
    public ArrayList<Usuario> getUsuario(String filtro) {
        ArrayList<Usuario> lista = new ArrayList();
        String sql = "select * from usuario";
        if (!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by nome";
        Conexao con = new Conexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(
                        new Usuario(rs.getString("documento"), rs.getString("genero"), rs.getString("estado"), rs.getString("nome"), rs.getString("tipo_usuario"), rs.getString("endereco"), rs.getDate("datanascimento").toLocalDate(),rs.getString("senha"),rs.getString("email")));
        } catch (Exception e) {
            System.out.println(e);
        }
        con.fecharConexao();
        return lista;
    }
}
