package com.faketri.market.security;

import com.faketri.market.service.jwt.JwtService;
import com.faketri.market.service.user.UserDetailsServerImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String                BEARER_PREFIX = "Bearer ";
    public static final String                HEADER_NAME   = "Authorization";
    private final       JwtService            jwtService;
    private final       UserDetailsServerImpl userDetailsServer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException, AuthenticationException {

        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,
                                                                       BEARER_PREFIX
        )) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            var jwt = authHeader.substring(BEARER_PREFIX.length());
            var username = jwtService.extractUserName(jwt);
            if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext()
                                                                         .getAuthentication() == null) {
                UserDetails userDetails =
                        userDetailsServer.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    SecurityContext context =
                            SecurityContextHolder.createEmptyContext();
                    var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            }
        } catch (ExpiredJwtException | SignatureException ex) {
            throw new AccessDeniedException(ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}