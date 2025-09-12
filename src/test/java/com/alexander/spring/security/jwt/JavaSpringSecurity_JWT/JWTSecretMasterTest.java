package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JWTSecretMasterTest {

    @Test
    public void generateSecretKey(){
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encodeKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("\nKey = " + encodeKey + "\n");
    }
}
