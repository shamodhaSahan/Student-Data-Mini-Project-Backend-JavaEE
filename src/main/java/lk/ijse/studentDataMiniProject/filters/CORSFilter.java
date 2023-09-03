package lk.ijse.studentDataMiniProject.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 7/22/2023
 * Time : 11:14 AM
 */
@WebFilter
//        (filterName = "CORSFilter",urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String origin = req.getHeader("Origin");
        if (origin.contains("http://localhost:3000")){
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,HEADER");
            res.setHeader("Access-Control-Allow-Headers", "content-type");
            res.setHeader("Access-Control-Expose-Header", "content-type");
        }
        chain.doFilter(req, res);
    }
}
