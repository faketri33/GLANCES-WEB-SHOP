package com.faketri.market.service.jwt;

import com.faketri.market.domain.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    /**
     * Extract user login from token
     *
     * @param token токен
     *
     * @return имя пользователя
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generate token by user fields
     *
     * @param userDetails data of user
     *
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("login", customUserDetails.getLogin());
            claims.put("email", customUserDetails.getEmail());
            claims.put("role", customUserDetails.getRole());
        }
        return generateToken(claims, userDetails);
    }

    /**
     * Checking the validity of the token
     *
     * @param token       token
     * @param userDetails data of user
     *
     * @return true, if token is valid
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return ( userName.equals(userDetails.getUsername()) ) && !isTokenExpired(
                token);
    }

    /**
     * Extract data from the token
     *
     * @param token           token
     * @param claimsResolvers function extract data
     * @param <T>             data type
     *
     * @return extracted data
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers
    ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Generate token
     *
     * @param extraClaims additional data
     * @param userDetails data of the user
     *
     * @return token
     */
    private String generateToken(Map<String, Object> extraClaims,
                                 UserDetails userDetails
    ) {
        return Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    /**
     * Checking the expired of the token
     *
     * @param token токен
     *
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract data about expiration
     *
     * @param token token
     *
     * @return date of expiration
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract data from token
     *
     * @param token token
     *
     * @return data from token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    /**
     * Get keys for token signatures
     *
     * @return key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
