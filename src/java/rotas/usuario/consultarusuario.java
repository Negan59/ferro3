
package rotas.usuario;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JWTTokenProvider;

@WebServlet(name = "consultarusuario", urlPatterns = {"/consultarusuario"})
public class consultarusuario extends HttpServlet {

    public String buscarUsuarios(String filtro) {
        String res = "";
        ArrayList<Usuario> usuarios = new DALUsuario().getUsuario(filtro); //erro DAL
        Gson gson=new Gson();
        res=gson.toJson(usuarios);

        return res;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        String usuario=request.getParameter("usuario");
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        try (PrintWriter out = response.getWriter()) {
            if(valida == "ok"){
                String filtro = request.getParameter("filtro");
                if (!filtro.isEmpty()|| filtro == null)
                    filtro = "upper(nome) like '%" + filtro.toUpperCase() + "%'";
               response.getWriter().print(buscarUsuarios(filtro));
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
