/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas.anuncio;

import bd.dal.DALAnuncio;
import bd.entidades.Anuncio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.JWTTokenProvider;

@MultipartConfig(
    location="/", 
    fileSizeThreshold=1024*1024,    // 1MB *      
    maxFileSize=1024*1024*100,      // 100MB **
    maxRequestSize=1024*1024*10*10  // 100MB ***
)
@WebServlet(name = "gravararquivo", urlPatterns = {"/gravararquivo"})
public class gravararquivo extends HttpServlet {

    public String arquivoNoServidor(String pasta,String filename,Part foto){
        OutputStream grava = null;
        InputStream filecontent = null;
        try { // criando a pasta
            File fpasta = new File(getServletContext().getRealPath("/") + "/" + pasta);
            fpasta.mkdir();
            grava = new FileOutputStream(new File(fpasta.getAbsolutePath() + "/" + filename));
            filecontent = foto.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                grava.write(bytes, 0, read);
            }
            grava.close();
            filecontent.close();
        } catch (Exception fne) {
            return "";
        }
        return  pasta+ "/" + filename;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LocalDate atual = null;
        String erro = "sucesso";
        int id;
        String usu=request.getParameter("usuario");
        String token = JWTTokenProvider.getToken(usu, "adm");
        String valida = JWTTokenProvider.validarToken(token);
        System.out.println("gravar arquivo");
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }
        DALAnuncio dal = new DALAnuncio();
        Anuncio a = dal.getAnuncio(id);
        String pasta = "fotos";
        Part filePart1 = request.getPart("foto1"); // Lê o arquivo de upload
        Part filePart2 = request.getPart("foto2"); // Lê o arquivo de upload
        Part filePart3 = request.getPart("foto3"); // Lê o arquivo de upload
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        String aux = myRandom.substring(0,8);
        String filename1 = "foto_"+a.getUsuario().getNome() + atual.now()+aux+ ".png";
        String filename2 = "foto2_"+a.getUsuario().getNome() + atual.now()+aux+ ".png";
        String filename3 = "foto3_"+a.getUsuario().getNome() + atual.now()+aux+ ".png";
        System.out.println(filename1);
        String foto1 = arquivoNoServidor(pasta,filename1,filePart1);
        String foto2 = arquivoNoServidor(pasta,filename2,filePart2);
        String foto3 = arquivoNoServidor(pasta,filename3,filePart3);
        a.setFoto1(foto1);
        a.setFoto2(foto2);
        a.setFoto3(foto3);
        if(valida == "ok"){
            if (!dal.alterar(a)) {
                erro = "Erro ao alterar anuncio";
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
