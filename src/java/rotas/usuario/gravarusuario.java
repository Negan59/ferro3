
package rotas.usuario;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JWTTokenProvider;

@WebServlet(name = "gravarusuario", urlPatterns = {"/gravarusuario"})
public class gravarusuario extends HttpServlet {


    //public Usuario(String documento, String genero, String id_estado, String nome, String tipo_usuario, String endereco, LocalDate dataNascimento)
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String documento;
        try {
            documento = request.getParameter("documento");
        } catch (Exception e) { documento = ""; }
        String genero = request.getParameter("genero");
        String estado = request.getParameter("estado");
        String nome = request.getParameter("nome");
        String tipo_usuario = request.getParameter("tipo_usuario");
        String endereco = request.getParameter("endereco");
        LocalDate datanascimento = LocalDate.parse(request.getParameter("datanascimento"));
                
        DALUsuario dal=new DALUsuario();
        String erro="Sucesso";
        Usuario usuario = new Usuario(documento, genero, estado, nome, tipo_usuario, endereco, datanascimento);
        String usu=request.getParameter("usuario");
        String token = JWTTokenProvider.getToken(usu, "adm");
        String valida = JWTTokenProvider.validarToken(token);
        System.out.println(usuario.getDocumento());
        System.out.println(usuario.getGenero());
        System.out.println(usuario.getEstado());
        System.out.println(usuario.getNome());
        System.out.println(usuario.getTipo_usuario());
        System.out.println(usuario.getEndereco());
        System.out.println(usuario.getDataNascimento());
        
        if(valida == "ok"){
                if (!dal.salvar(usuario)) {
                    erro = "Erro ao gravar o usuario";
                }
        }
        else{
            response.getWriter().print("não autorizado");
        }
        response.getWriter().print(erro);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
