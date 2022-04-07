package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/counter/clear")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        if (cookies != null && Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .anyMatch(cookie -> cookie.getValue().length() > 10))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            ((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {

    }
}
