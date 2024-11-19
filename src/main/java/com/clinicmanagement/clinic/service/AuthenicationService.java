package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.auth.AuthenicationRequest;
import com.clinicmanagement.clinic.dto.auth.AuthenicationResponse;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.repository.UserRopsitory;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowWebServer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenicationService {
    @Autowired
    UserRopsitory userRopsitory;

    @NonFinal
    @Value("${security.signerkey}")
    protected String SIGNER_KEY;

//    public AuthenicationResponse authenicate(AuthenicationRequest request){
//        var user = userRopsitory.findByUsername(request.getUsername())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        boolean authenicated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//
//        if(!authenicated) throw new AppException(ErrorCode.UNAUTHENICATED);
//
//        var token = createToken(user);
//        var username = request.getUsername();
//        return AuthenicationResponse.builder()
//                .token(token)
//                .username(username)
//                .authenicated(true)
//                .build();
//
//    }

//    private String createToken(Useracount user){
//        //Tao header cho token
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//        //set Payload
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(user.getUsername()) // title
//                .issuer("clinic.com") // issue
//                .issueTime(new Date()) //thoi gian tao token
//                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))) // time het han token
//                .claim("scope",buildScope(user)) //
//                .build();
//
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//
//        //tao token
//        JWSObject jwsObject = new JWSObject(header,payload);
//
//        //Ki token
//        try {
//            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            log.error("Cannot create token",e);
//            throw new RuntimeException(e);
//        }
//    }
    private String buildScope(Useracount user){
        StringJoiner stringJoiner = new StringJoiner("");
        if(!CollectionUtils.isEmpty(user.getRole())){
            user.getRole().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
