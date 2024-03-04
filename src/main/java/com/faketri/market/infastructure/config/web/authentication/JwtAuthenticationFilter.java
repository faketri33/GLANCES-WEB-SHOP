package com.faketri.market.infastructure.config.web.authentication;

import com.faketri.market.usecase.auth.JwtServiceImpl;
import com.faketri.market.usecase.user.UserDetailsServerImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The type Jwt authentication filter.
 *
 * @author Dmitriy Faketri
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * The constant BEARER_PREFIX.
     */
    public static final String                BEARER_PREFIX = "Bearer ";
    /**
     * The constant HEADER_NAME.
     */
    public static final String                HEADER_NAME   = "Authorization";
    private final       JwtServiceImpl        jwtService;
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
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        var username = jwtService.extractUserName(jwt);
        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext()
                                                                     .getAuthentication() == null) {
            UserDetails userDetails =
                    userDetailsServer.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {

                log.info("User with login - " + username + " authorization");
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
        filterChain.doFilter(request, response);
    }

}