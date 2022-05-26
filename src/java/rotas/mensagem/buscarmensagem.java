
package rotas.mensagem;

import bd.dal.DALMensagem;
import bd.entidades.Mensagem;
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
@WebServlet(name = "buscarmensagem", urlPatterns = {"/buscarmensagem"})
public class buscarmensagem extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       int id;
       String usu=request.getParameter("usuario");
        String token = JWTTokenProvider.getToken(usu, "adm");
        String valida = JWTTokenProvider.validarToken(token);
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }
        try (PrintWriter out = response.getWriter()) {
            DALMensagem dal = new DALMensagem();
            Erro erro = new Erro("", "");
            Mensagem a = dal.getMensagem(id);
            if(valida == "ok"){
            if (a == null) {
                erro.setTipo("Erro");
                erro.setMens("Problemas ao carregar o Mensagem");
                out.print(new Gson().toJson(erro));
            } else
                out.print(new Gson().toJson(a));
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
