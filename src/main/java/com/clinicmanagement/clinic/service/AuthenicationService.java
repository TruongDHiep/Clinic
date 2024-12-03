package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.auth.AuthenicationRequest;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenicationService {
//    @Autowired
//    UserRepository userRopsitory;
//
//    @Autowired
//    JwtDecoder jwtDecoder;
//
//    @NonFinal
//    @Value("${security.signerkey}")
//    protected String SIGNER_KEY;

//    public AuthenicationResponse authenicate(AuthenicationRequest request){
//        var user = userRopsitory.findByUsername(request.getUsername())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//
//        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENICATED);
//
//        // Tạo token
//        var token = createToken(user);
//
//        Jwt jwt = jwtDecoder.decode(token);
//        String scopes = jwt.getClaim("scope");
//        var authorities = Arrays.stream(scopes.split(" "))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                user.getUsername(),
//                null,
//                authorities
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        var currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
//        if (currentAuthentication != null && currentAuthentication.isAuthenticated()) {
//            System.out.println("Người dùng đã được đăng ký:");
//            System.out.println("Username: " + currentAuthentication.getName());
//            System.out.println("Roles: " + currentAuthentication.getAuthorities());
//        } else {
//            System.out.println("Người dùng chưa được đăng ký trong SecurityContextHolder.");
//        }
//
//        return AuthenicationResponse.builder()
//                .token(token)
//                .username(user.getUsername())
//                .authenicated(true)
//                .build();
//
//    }
//    public String getTokenFromCookie(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
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
//    private String buildScope(Useracount user){
//        StringJoiner stringJoiner = new StringJoiner("");
//        if(!CollectionUtils.isEmpty(user.getRole())){
//            user.getRole().forEach(stringJoiner::add);
//        }
//        return stringJoiner.toString();
//    }
}
