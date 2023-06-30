package br.ufsm.csi.poow2.spring_rest_security.security;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/*
 JWT -- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt/0.9.1
 jaxb -- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
* */

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofSeconds(10000).toMillis();

    public String geraToken(Funcionario funcionario){
        System.out.println("JWT UTIL:"+funcionario.getCargo());

        final Map<String,Object> claims = new HashMap<>();
        claims.put("sub",funcionario.getEmail_func());
        claims.put("permissoes: ",funcionario.getCargo());

        return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis()+this.TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256,"poow2").compact();
    }

    public String getUsernameToken(String token){
        if(token !=null){
            return this.parseToken(token).getSubject();
        }else{
            return null;
        }
    }

    public boolean isTokenExpirado(String token){
        return this.parseToken(token).getExpiration().before(new Date());
    }

    public Claims parseToken(String token){
        String cleanToken = token != null ? token.replace("Bearer", "") : null;
        return Jwts.parser()
                .setSigningKey("poow2")
                .parseClaimsJws(cleanToken)
                .getBody();
    }

}
