package lk.ijse.studentDataMiniProject.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/22/2023
 * Time : 1:12 PM
 */

@WebFilter
public class Security extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("security");
        chain.doFilter(req, res);
    }
}
