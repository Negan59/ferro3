/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas.usuario;

import bd.dal.DALUsuario;
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

/**
 *
 * @author Aluno
 */
@WebServlet(name = "apagarusuario", urlPatterns = {"/apagarusuario"})
public class apagarusuario extends HttpServlet {
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        System.out.println("entra no apagar?");
        String documento;
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        try {
            documento = (request.getParameter("documento"));
        } catch (Exception e) {
            documento = "";
        }
        try (PrintWriter out = response.getWriter()) {
            DALUsuario dal=new DALUsuario();
            Erro erro = new Erro("ok","sucesso");
            if(valida == "ok"){
                if (!dal.apagar(documento))
                {
                     erro.setTipo("Erro");
                     erro.setMens("Problemas ao apagar o usuário");
                }
                out.print(new Gson().toJson(erro));
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
