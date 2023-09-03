package lk.ijse.studentDataMiniProject.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/22/2023
 * Time : 1:11 PM
 */

@WebFilter
public class Log extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("log");
        chain.doFilter(req, res);
    }
}
