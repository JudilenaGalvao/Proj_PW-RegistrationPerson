package com.example.ProjetoPw;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class Vagas_pessoasController {

   

    @RequestMapping(method = RequestMethod.POST, value = "/doLogin")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("judilena") && password.equals("123")){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);

            response.sendRedirect("/doTestes");

        }else{
            response.sendRedirect("paginaLogin.html");
        }

    }


    @RequestMapping(value = "/doTestes", method = RequestMethod.GET)
    public void doTestes(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        String pessoa = request.getParameter("pessoa");

        Cookie c = new Cookie("nome", pessoa);
        c.setMaxAge(60);

        response.addCookie(c);

        Cookie[] cookies = request.getCookies();

        for (Cookie c1: cookies){
            response.getWriter().println(c1.getValue());
        }



        //LOGIN
        Boolean logado = (Boolean) session.getAttribute("logado");

        if (logado != null && logado == true){
            response.getWriter().println("Logado");
        }else{
            response.getWriter().println("Deslogado");
        }

    }

    @RequestMapping(value = "/testeFiltro", method = RequestMethod.GET)
    public void exemploFiltro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.getWriter().println("Cadastre-se agora");
    }


    @RequestMapping( method = RequestMethod.POST, value = "/doCadastrar")
    public void cadastrarPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var t = new Vagas_pessoas();
        var nome = request.getParameter("nome");
        var idade = Integer.parseInt(request.getParameter("idade"));
        var telefone = request.getParameter("telefone");
        var email = request.getParameter("email");
        var arqv_curriculo = request.getParameter("arqv_curriculo");


        t.setNome(nome);
        t.setIdade(idade);
        t.setTelefone(telefone);
        t.setEmail(email);
        t.setArqv_curriculo(arqv_curriculo);

        Vagas_pessoasDao dao = new Vagas_pessoasDao();
        dao.CadastrarPessoa(t);
        response.sendRedirect("/index.html");
    }



    @RequestMapping(method = RequestMethod.GET, value = "/doListar")
    public void listarPessoas(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var dao = new Vagas_pessoasDao();
        var writer = response.getWriter();

        var listarPessoas = dao.listarTodasPessoas();
        response.setContentType("text/HTML");

        writer.println("<html>" +
                "<body>");

        for (var t1 : listarPessoas){
            writer.println("<p><strong>NOME: </strong>" +t1.getNome() + "</p>");
            writer.println("<p><strong>IDADE: </strong>" +t1.getIdade() + "</p>");
            writer.println("<p><strong>TELEFONE: </strong>" +t1.getTelefone() + "</p>");
            writer.println("<p><strong>EMAIL: </strong>" +t1.getEmail() + "</p>");
            writer.println("<p><strong>CURRICULO: </strong>" +t1.getArqv_curriculo() + "</p>");
            writer.println("<a href='doEditarPagina?id="+t1.getId()+"'>Editar</a>");
            writer.println("<a href='doExcluirPagina?id="+t1.getId()+"'>Excluir</a>");
            writer.println("<hr/>");
            writer.println("<hr/>");
        }

        writer.println("</html>" +
                "</body>");

    }


    @RequestMapping(value = "/doBuscar", method = RequestMethod.GET)
    public void doBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new Vagas_pessoasDao();

        Vagas_pessoas t = dao.buscarpessoaid(id);
        var writer = response.getWriter();

        writer.println("<html>" +
                "<body>");


        if (t != null){
            writer.println("<hr/>");
            writer.println("<hr/>");
            writer.println("<p><strong>NOME: </strong>" +t.getNome() + "</p>");
            writer.println("<p><strong>IDADE: </strong>" +t.getIdade() + "</p>");
            writer.println("<p><strong>TELEFONE: </strong>" +t.getTelefone() + "</p>");
            writer.println("<p><strong>EMAIL: </strong>" +t.getEmail() + "</p>");
            writer.println("<p><strong>CURRICULO: </strong>" +t.getArqv_curriculo() + "</p>");
            writer.println("<a href='doEditarPagina?id="+t.getId()+"'>Editar</a>");
            writer.println("<a href='doExcluirPagina?id="+t.getId()+"'>Excluir</a>");
            writer.println("<hr/>");
            writer.println("<hr/>");
        }else{
            writer.println("<p> Não encontrado </p>");
        }
        writer.println("</html>" +
                "</body>");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/doEditarPagina")
    public void doEditarPagina(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new Vagas_pessoasDao();

        Vagas_pessoas t = dao.buscarpessoaid(id);

        var writer = response.getWriter();

        writer.println("<html>" +
                "<body>" +
                "<form action='doAtualizar' method='post'>");
        writer.println("<hr/>");
        writer.println("<hr/>");
        writer.println("<center>");
        writer.println("<h2>Atualizar dados:</h2>");
        writer.println("<hr/>");
        writer.println("<hr/>");
        writer.println("</br></br> </br></br> </br>");
        writer.println("<p><input type='hidden' name='id' value='" + t.getId() + "'></p>");
        writer.println("<p><input type='text' name='nome' value='" + t.getNome() + "'></p>");
        writer.println("<p><input type='number' name='idade' value='" + t.getIdade() + "'></p>");
        writer.println("<p><input type='text' name='telefone' value='" + t.getTelefone() + "'></p>");
        writer.println("<p><input type='text' name='email' value='" + t.getEmail() + "'></p>");
        writer.println("<p><input type='text' name='arqv_curriculo' value='" + t.getArqv_curriculo() + "'></p>");
        writer.println("<p><button type='submit'>Atualizar</button></p>");
        writer.println("</br></br> </br></br> </br>");
        writer.println("<hr/>");
        writer.println("<hr/>");
        writer.println("</center>");


        writer.println("</form>" +
                "</body>" +
                "<html>");
    }



    @RequestMapping(method = RequestMethod.POST, value = "/doAtualizar")
    public void doAtualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var nome = request.getParameter("nome");
        var idade = Integer.parseInt(request.getParameter("idade"));
        var telefone = request.getParameter("telefone");
        var email = request.getParameter("email");
        var arqv_curriculo = request.getParameter("arqv_curriculo");

        var vagas_pessoas = new Vagas_pessoas(id, nome, idade, telefone, email, arqv_curriculo);
        var dao = new Vagas_pessoasDao();

        dao.atualizarpessoa(vagas_pessoas);

        response.sendRedirect("doListar");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/doExcluirPagina")
    public void doExcluirPagina(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new Vagas_pessoasDao();

        Vagas_pessoas t = dao.buscarpessoaid(id);

        var writer = response.getWriter();

        writer.println("<html>" +
                "<body>" +
                "<form action='doExcluir' method='post'>");
        writer.println("<hr/>");
        writer.println("<hr/>");
        writer.println("<center>");
        writer.println("<p><h2>Deseja mesmo excluir esses dados?<h2></p>");
        writer.println("<hr/>");
        writer.println("<hr/>");
        writer.println("</br></br>");
        writer.println("<p><input type='hidden' name='id' value='" + t.getId() + "'></p>");
        writer.println("<p><input type='text' name='nome' value='" + t.getNome() + "'></p>");
        writer.println("<p><input type='number' name='idade' value='" + t.getIdade() + "'></p>");
        writer.println("<p><input type='text' name='telefone' value='" + t.getTelefone() + "'></p>");
        writer.println("<p><input type='text' name='email' value='" + t.getEmail() + "'></p>");
        writer.println("<p><input type='text' name='arqv_curriculo' value='" + t.getArqv_curriculo() + "'></p>");
        writer.println("<p><button type='submit'>Excluir</button></p>");
        writer.println("</br></br>");
        writer.println("</center>");
        writer.println("<hr/>");
        writer.println("<hr/>");

        writer.println("</form>" +
                "</body>" +
                "<html>");
    }


    @RequestMapping(method = RequestMethod.POST, value = "/doExcluir")
    public void doExcluir(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var nome = request.getParameter("nome");
        var idade = Integer.parseInt(request.getParameter("idade"));
        var telefone = request.getParameter("telefone");
        var email = request.getParameter("email");
        var arqv_curriculo = request.getParameter("arqv_curriculo");

        var vagas_pessoas = new Vagas_pessoas(id, nome, idade, telefone, email, arqv_curriculo);
        var dao = new Vagas_pessoasDao();

        dao.deletarpessoa(vagas_pessoas);

        response.sendRedirect("doListar");

    }



}
