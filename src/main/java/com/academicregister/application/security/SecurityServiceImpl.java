package com.academicregister.application.security;

import com.academicregister.domain.student.Student;
import com.academicregister.domain.user.IUserRepository;
import com.academicregister.domain.user.User;
import com.academicregister.shared.exception.student.StudentNotFoundException;
import com.academicregister.shared.exception.user.NotValidLoginException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Try;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements ISecurityService {

    private final IUserRepository userRepository;

    public SecurityServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getJWTToken(String username) {
        String secretKey = "academicSecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("academicId")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Basic " + token;
    }

    @Override
    public String encryptText(String text) throws NoSuchAlgorithmException {
        var builder = new StringBuilder();
        var m = MessageDigest.getInstance("MD5");
        m.update(text.getBytes());
        byte[] bytes = m.digest();

        for(byte b : bytes){
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        var encryptedText = builder.toString();

        System.out.println("Plain-text password: " + text);
        System.out.println("Encrypted password using MD5: " + encryptedText);

        return encryptedText;
    }

    @Override
    public Boolean isValidLogin(String username, String password) {
        Try<User> rsUser = Try.of(() -> userRepository.findUserByUsernameAndPassword(username, password));
        rsUser.getOrElseThrow(throwable -> {
            throw new NotValidLoginException();
        });
        return true;
    }
}
