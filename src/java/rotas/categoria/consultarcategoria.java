/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas.categoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.dal.DALCategoria;
import bd.entidades.Categoria;
import com.google.gson.Gson;
import util.JWTTokenProvider;

@WebServlet(name = "consultarcategoria", urlPatterns = { "/consultarcategoria" })
public class consultarcategoria extends HttpServlet {

    public String buscaCategorias(String filtro) {
        String res = "";
        ArrayList<Categoria> cat = new DALCategoria().getCategoria(filtro);
        Gson gson = new Gson();
        res = gson.toJson(cat);
        return res;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usu=request.getParameter("usuario");
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        try (PrintWriter out = response.getWriter()) {
            String filtro = request.getParameter("filtro");
            System.out.println(filtro);
            if(valida == "ok"){
                if (!filtro.isEmpty() || filtro == null)
                    filtro = "upper(nome) like '%" + filtro.toUpperCase() + "%'";
                response.getWriter().print(buscaCategorias(filtro));
            }
            else{
                response.getWriter().print("não autorizado");
            }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
