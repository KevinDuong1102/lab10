package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class AdminAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        UserDB udb = new UserDB();
        User user = udb.get(email);
        if (user.getRole().getRoleId() != 1) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("notes");
            return;
        }

//        if (user.getRole().getRoleId() == 1) {
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.sendRedirect("admin");
//            return;
//
//        }

        //this will either call upon the next filter in the chain or it will load the requested servlet
        chain.doFilter(request, response);

        // any code after the chain.doFilter will be executed after the servlet
    }

    @Override
    public void destroy() {

    }

}
