
package util;

import bd.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.claims;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;

public class JWTTokenProvider {
    static private final SecretKey CHAVE = Keys.hmacShaKeyFor("MINHASENHASECRETA_MINHASENHASECRETA".getBytes(StandardCharsets.UTF_8));

        static public String getToken(String usuario, String senha,Usuario u)
        {
         
            String jwtToken = Jwts.builder()
            .setSubject(usuario)
            .setIssuer("localhost:8080")
            .claim("tipo_usuario",u.getTipo_usuario())
            .claim("documento", u.getDocumento())
            .setIssuedAt(new Date())
            .setExpiration(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(CHAVE)
            .compact();
            return jwtToken;
        }
        
        static public String validarToken(String token)
        {
            String res="ok";
            try {
                Jwts.parserBuilder()
                        .setSigningKey(CHAVE)
                        .build()
                        .parseClaimsJws(token).getSignature();
            } catch (Exception e) {
                System.out.println(e);
            }
            return res;
        }
        
        public Claims getAllClaimsFromToken(String token) 
        {
            Claims claims=null;
            try {
                claims = Jwts.parserBuilder()
                        .setSigningKey(CHAVE)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                System.out.println("Erro ao recuperar as informações (claims)");
            }

            return claims;
        }    

}
