package com.example.hospital.Security;


import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends OncePerRequestFilter {
    private final String header="Authorization";
    private final String prefix="Bearer ";
    private final String secret="HRProject-api&&&&14";

    @Override
    protected void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (CheckJWTToken(request, response)) {
                Claims claims = ValidateToken(request);
                // claims.get("");
                // claims.get(claims)
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {

                    // SecurityContextHolder.clearContext();
                    SecurityContextHolder.createEmptyContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }


    private Claims ValidateToken(HttpServletRequest request){
        String jwtToken=request.getHeader(this.header).replace(this.prefix,"");
        if(jwtToken!=null){

            String user= Jwts.parser().setSigningKey(secret.getBytes())
                    .parseClaimsJws(jwtToken.replace(this.prefix,""))
                    .getBody()
                    .getSubject();
        }
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Authentication method in Spring flow
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                null);

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean CheckJWTToken(HttpServletRequest request, HttpServletResponse response) {

        String authontecationHeader = request.getHeader(this.header);
        if (authontecationHeader == null ||!authontecationHeader.startsWith(this.prefix))
            return  false;
        return  true;

    }
}
