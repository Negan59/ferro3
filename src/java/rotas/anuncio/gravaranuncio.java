package rotas.anuncio;

import bd.dal.DALAnuncio;
import bd.dal.DALCategoria;
import bd.dal.DALUsuario;
import bd.entidades.Anuncio;
import bd.entidades.Categoria;
import bd.entidades.Usuario;
import io.jsonwebtoken.Claims;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.JWTTokenProvider;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "gravaranuncio", urlPatterns = { "/gravaranuncio" })
public class gravaranuncio extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String conteudo = request.getParameter("conteudo");
        String token=request.getParameter("token");
        LocalDate data = LocalDate.now();
        String status = "R";
        String p = request.getParameter("produto");
        String t = request.getParameter("titulo");
        Claims teste = new JWTTokenProvider().getAllClaimsFromToken(token);
        Usuario u = new DALUsuario().getUsuarioUnico((String) teste.get("documento"));
        Categoria cat = new DALCategoria().getCategoria(Integer.parseInt(request.getParameter("categoria")));
        DALAnuncio dal = new DALAnuncio();
        String erro = "Sucesso";
        String usu=request.getParameter("usuario");
        
        String valida = JWTTokenProvider.validarToken(token);
        
        Anuncio anu = new Anuncio(conteudo, data, p, cat, "", "", "", status, u,t);
        if(valida == "ok"){
            if (!dal.salvar(anu)) {
                erro = "Erro ao gravar anuncio";
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
