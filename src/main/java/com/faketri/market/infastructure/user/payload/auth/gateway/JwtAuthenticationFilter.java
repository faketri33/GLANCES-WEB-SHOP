package com.faketri.market.infastructure.user.payload.auth.gateway;

import com.faketri.market.infastructure.config.exception.JwtValidException;
import com.faketri.market.usecase.user.payload.user.UserDetailsServerImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JwtService jwtService;
    private final UserDetailsServerImpl userDetailsServer;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServerImpl userDetailsServer) {
        this.jwtService = jwtService;
        this.userDetailsServer = userDetailsServer;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain
    ) throws ServletException, IOException, AuthenticationException {

        String token = getTokenFromRequest(request);

        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (!jwtService.validateAccessToken(token))
                throw new JwtValidException("Not valid token");

            String username = jwtService.extractUserName(token);

            if (StringUtils.isNotEmpty(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsServer.loadUserByUsername(username);

                SecurityContext context =
                        SecurityContextHolder.createEmptyContext();
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
                log.info("User with login - " + username + " authorization");

            }
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(HEADER_NAME);
        if (StringUtils.isNotEmpty(bearer) && bearer.startsWith(BEARER_PREFIX))
            return bearer.substring(7);
        return "";
    }

}