package com.example.ProjetoPw;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/testeFiltro")

public class Filtro implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("Passou pelo filtro");

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        Boolean logado = (Boolean) session.getAttribute("logado");

        if (logado != null && logado == true){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).sendRedirect("paginaLogin.html");
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
