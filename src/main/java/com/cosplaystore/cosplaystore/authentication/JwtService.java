package com.cosplaystore.cosplaystore.authentication;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.model.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {
    private final String key = "tD3ExPN/BFIefbJ9uZfWzlBFUdTZoJ2qKozVjGg7YDLjDidCxfJSSpCEsqU+dDsX";

    public Object verifyJWTToken(String token) {
        try {
            JWSVerifier verifier = new MACVerifier(key.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            Date time = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (!time.after(new Date())) {
                return false;
            }
            Boolean verified = (Boolean) signedJWT.verify(verifier);
            if (verified) {
                return signedJWT.getJWTClaimsSet();
            }
            return verified;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;

        } catch (JOSEException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String generateToken(User user, Date expire) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("https://apitraining.com")
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(expire)
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", user.getRole())
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        // Apply the HMAC protection
        try {
            jwsObject.sign(new MACSigner(key.getBytes()));
        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        String token = jwsObject.serialize();
        return token;
    }

    public String getJWTAccessToken(User user) {
        String token = generateToken(user, new Date(Instant.now().plus(30, ChronoUnit.SECONDS).toEpochMilli()));

        return token;
    }

    public String getJWTRefreshToken(User user) {
        String token = generateToken(user, new Date(Instant.now().plus(30, ChronoUnit.DAYS).toEpochMilli()));

        return token;
    }
}
