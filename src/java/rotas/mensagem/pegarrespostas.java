/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package rotas.mensagem;

import bd.dal.DALMensagem;
import bd.entidades.Mensagem;
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

/**
 *
 * @author gui
 */
@WebServlet(name = "pegarrespostas", urlPatterns = {"/pegarrespostas"})
public class pegarrespostas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String buscaMensagens(int cod,int id) {
        String res = "";
        System.out.println("para aqui");
        ArrayList<Mensagem> mens = new DALMensagem().getResposta(cod,id);
        Gson gson = new Gson();
        res = gson.toJson(mens);
        return res;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usu=request.getParameter("usuario");
        String token=request.getParameter("token");
        int cod=Integer.parseInt(request.getParameter("cod"));
        String valida = JWTTokenProvider.validarToken(token);
        System.out.println("valida = "+valida);
        int id = Integer.parseInt(request.getParameter("id"));
        try (PrintWriter out = response.getWriter()) {
            if(valida == "ok"){
                System.out.println("shazam carai");
            response.getWriter().print(buscaMensagens(cod,id));
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
