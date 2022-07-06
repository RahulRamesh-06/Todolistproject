package com.deloitte.project.filters;

import com.deloitte.project.service.Authservice;
import com.deloitte.project.service.UserlistService;
import com.deloitte.project.util.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *  JWT filter class for addtional configuration in authenticating the apis's using JWT.
 *
 * @author Rahul Ramesh
 *
 */

@Component
public class Jwtfilter extends OncePerRequestFilter {

    @Autowired
    private UserlistService userlistService;
    @Autowired
    private Jwtutil jwtutil;
    @Autowired
    private Authservice authservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String username=null;
        String jwt=null;
        final String authHeader=request.getHeader("Authorization");
        System.out.println("the value in header is "  +authHeader);
        if(authHeader!=null && authHeader.startsWith("Bearer ")) {
            System.out.println("coming inside if we have jwt");
            jwt=authHeader.substring(7);
            System.out.println(jwt);
            username=jwtutil.getUsernameFromToken(jwt);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails=authservice.loadUserByUsername(username);
            if(jwtutil.validateToken(jwt,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        chain.doFilter(request,response);
    }
}
