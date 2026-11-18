package com.vtuber.wafuseph_backend.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

public class KeyGenerator {
   void main() {
       
    SecretKey key = Jwts.SIG.HS256.key().build();

    String base64Key = Encoders.BASE64.encode(key.getEncoded());
        IO.print(base64Key);
   }
}
