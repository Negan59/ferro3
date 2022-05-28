/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas.categoria;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.dal.DALCategoria;
import bd.entidades.Categoria;
import util.JWTTokenProvider;

@WebServlet(name = "incluircategoria", urlPatterns = { "/incluircategoria" })
public class incluircategoria extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usu=request.getParameter("usuario");
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        String nome = request.getParameter("nome");
        System.out.println(nome);
        DALCategoria dal = new DALCategoria();
        String erro = "Sucesso";
        Categoria cat = new Categoria(nome);
        if(valida == "ok"){
            if (!dal.salvar(cat)) {
                erro = "Erro ao gravar categoria";
            }
            response.getWriter().print(erro);
        }
        else{
            response.getWriter().print("não autorizado");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
