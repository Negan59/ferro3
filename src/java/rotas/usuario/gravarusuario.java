package rotas.usuario;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JWTTokenProvider;

@WebServlet(name = "gravarusuario", urlPatterns = {"/gravarusuario"})
public class gravarusuario extends HttpServlet {

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    //public Usuario(String documento, String genero, String id_estado, String nome, String tipo_usuario, String endereco, LocalDate dataNascimento)

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String documento;
        try {
            documento = request.getParameter("documento");
        } catch (Exception e) {
            documento = "";
        }
        
        String genero = request.getParameter("genero");
        String estado = request.getParameter("estado");
        String nome = request.getParameter("nome");
        String tipo_usuario = request.getParameter("tipo_usuario");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String csenha = request.getParameter("csenha");
        String regex = "/[A-Z][a-z]* [A-Z][a-z]*/";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        boolean macthFound = matcher.find();
        LocalDate datanascimento = LocalDate.parse(request.getParameter("datanascimento"));
        LocalDate dataatual = LocalDate.now();
        LocalDate datalimite = LocalDate.of(1910, Month.MARCH, 10);
        
        DALUsuario dal = new DALUsuario();
        String erro = "Sucesso";
        if (isCPF(documento)) {
            if (nome.matches("[[A-Z][a-z]* ]*")) {
                if (senha.equals(csenha) && senha.length()>5) {
                    if(!datanascimento.isAfter(dataatual) && !datanascimento.isBefore(datalimite) ){
                    Usuario usuario = new Usuario(documento, genero, estado, nome, tipo_usuario, endereco, datanascimento, senha, email);
                    String token=request.getParameter("token");
                    String valida = JWTTokenProvider.validarToken(token);
                    if (valida == "ok") {
                        if (!dal.salvar(usuario)) {
                            erro = "Erro ao gravar o usuario";
                        }
                    } else {
                        response.getWriter().print("não autorizado");
                    }
                    }else{
                        erro = "Erro ao gravar o usuario, data inválida";
                    }
                } else {
                    erro = "Erro ao gravar o usuario, senha inválida";
                }
            } else {
                erro = "Erro ao gravar o usuario, Nome contém caracteres inválidos";
            }
        } else {
            erro = "Erro ao gravar o usuario, CPF não é válido";
        }
        response.getWriter().print(erro);
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
