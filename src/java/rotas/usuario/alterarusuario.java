/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas.usuario;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JWTTokenProvider;

/**
 *
 * @author marin
 */
@WebServlet(name = "alterarusuario", urlPatterns = {"/alterarusuario"})
public class alterarusuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String erro = "Sucesso";
        String usu=request.getParameter("usuario");
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        try {
            email = (request.getParameter("email"));
        } catch (Exception e) {
            erro = "Email não existe!!!";
            response.getWriter().print(erro);
        }
        String nome = request.getParameter("nome");
        DALUsuario dal = new DALUsuario();
        
        Usuario u = dal.getUsuarioUnicoE(email);
        if(valida == "ok"){
            if (!dal.alteraE(email, senha)) {
                erro = "Erro ao alterar senha";
            }
            response.getWriter().print(erro);
        }
        else{
            response.getWriter().print("não autorizado");
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
