
package rotas.mensagem;

import bd.dal.DALAnuncio;
import bd.dal.DALMensagem;
import bd.dal.DALUsuario;
import bd.entidades.Anuncio;
import bd.entidades.Mensagem;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JWTTokenProvider;

@WebServlet(name = "gravarmensagem", urlPatterns = {"/gravarmensagem"})
public class gravarmensagem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String conteudo = request.getParameter("conteudo");
        LocalDate d = LocalDate.parse(request.getParameter("data"));
         Usuario u = new DALUsuario().getUsuarioUnico(request.getParameter("documento"));
        Mensagem mens;
        if(Integer.parseInt(request.getParameter("id_mensagem"))!= 0){
             mens = new DALMensagem().getMensagem(Integer.parseInt(request.getParameter("id_mensagem")));
        }
        else{
            mens = new Mensagem();
        }
        Anuncio anun = new DALAnuncio().getAnuncio(Integer.parseInt(request.getParameter("id_anuncio")));
        DALMensagem dal = new DALMensagem();
        
        String erro = "Sucesso";
        
        String usu=request.getParameter("usuario");
        String token=request.getParameter("token");
        String valida = JWTTokenProvider.validarToken(token);
        Mensagem m = new Mensagem(anun,u,conteudo, d,mens);
        System.out.println("id_anuncio : "+m.getAnuncio().getId());
        System.out.println("documento : "+m.getUsuario().getDocumento());
        System.out.println("conteudo : "+m.getConteudo());
        System.out.println("data : "+m.getData());
        System.out.println("mens : "+request.getParameter("id_mensagem"));
        System.out.println(m.getMens().getId());
        if(valida == "ok"){
        if (mens.getId() !=0) {
            dal.salvar(m);
        }
        else{
            dal.salvarPergunta(m);
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
