package com.bxdlove.worktimeserver.filter;

import com.bxdlove.worktimeserver.beans.AdminLoginBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * The {@code AdminDashboardFilter} prevents non-authenticated users to open the administrator area of the application.
 *
 * @author Gregor Gottschewski
 */
@WebFilter(
        filterName = "admin dashboard filter",
        urlPatterns = {"/admin/*"}
)
public class AdminDashboardFilter implements Filter {
    @Inject
    private AdminLoginBean adminLoginBean;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (adminLoginBean.isLoggedIn()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/admin/login.xhtml");
        dispatcher.forward(servletRequest, servletResponse);
    }
}
