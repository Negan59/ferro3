
package rotas.usuario;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Erro;
import util.JWTTokenProvider;


@WebServlet(name = "buscarusuario", urlPatterns = {"/buscarusuario"})
public class buscarusuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String documento;
        String usuario=request.getParameter("usuario");
        String token = JWTTokenProvider.getToken(usuario, "adm");
        String valida = JWTTokenProvider.validarToken(token);
        try {
            documento = (request.getParameter("documento"));
        } catch (Exception e) {
            documento = "";
        }
        try (PrintWriter out = response.getWriter()) {
            if(valida == "ok"){
                DALUsuario dal=new DALUsuario();
                Erro erro = new Erro("","");
                Usuario u = dal.getUsuarioUnico(documento);
                if (u==null)
                {
                    erro.setTipo("Erro");
                    erro.setMens("Problemas ao carregar o usuário");
                    out.print(new Gson().toJson(erro));
                }
                else
                    out.print(new Gson().toJson(u));
            }
            else{
                response.getWriter().print("não autorizado");
            }
        }
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
