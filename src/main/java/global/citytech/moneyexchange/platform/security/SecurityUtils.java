package global.citytech.moneyexchange.platform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Named("securityUtils")
public class SecurityUtils {
    @Inject
    public SecurityUtils() {
    }

    public String token(int userId, String email, String userRole,String userStatus,String blacklist) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.putAll(prepareRequestBody(userId,email, userRole,userStatus,blacklist));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 12);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,getKey())
                .setExpiration(calendar.getTime())
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
    }

    private Key getKey() {
        String jwtSignature = "9f1ba31f-86b8-42aa-9449-1eed715b464d";
        return Keys.hmacShaKeyFor(jwtSignature.getBytes(StandardCharsets.UTF_8));
    }

    private Map<String, Object> prepareRequestBody(int userId,String email, String userRole,String userStatus,String blacklist) {
        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("email", email);
        requestBody.put("userId", userId);
        requestBody.put("userRole", userRole);
        requestBody.put("userStatus", userStatus);
        requestBody.put("checkBlacklist", blacklist);
        return requestBody;
    }

    public RequestContext parseTokenAndGetContext(String token) {
        try {
            Claims claims = this.parseToken(token);
            RequestContextBuilder contextBuilder = RequestContextBuilder.builder();
            contextBuilder.email(claims.get("email", String.class));
            contextBuilder.userID(claims.get("userId", String.class));
            contextBuilder.userRole(claims.get("userRole", String.class));
            return contextBuilder.build();

        } catch (Exception exception) {
            if (exception instanceof ExpiredJwtException)
                throw new IllegalArgumentException("Security Token is expired");
            throw new IllegalArgumentException("Security Token is invalid");
        }
    }

}

