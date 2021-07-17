package uz.mk.newssiteapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.mk.newssiteapp.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "neverfindthispassword";

    public String generateToken(String username, Role role) {
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        } catch (Exception e) {
            return null;
        }
    }
}
