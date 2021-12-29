package com.academicregister.application.security;

import com.academicregister.domain.permission.IPermissionRepository;
import com.academicregister.domain.permission.Permission;
import com.academicregister.domain.resource.IResourceRepository;
import com.academicregister.domain.user.IUserRepository;
import com.academicregister.domain.user.User;
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
    private final IPermissionRepository permissionRepository;
    private final IResourceRepository resourceRepository;

    public SecurityServiceImpl(IUserRepository userRepository, IPermissionRepository permissionRepository, IResourceRepository resourceRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.resourceRepository = resourceRepository;
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
    public String encryptText(String text) {
        var builder = new StringBuilder();
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(text.getBytes());
        byte[] bytes = m.digest();

        for(byte b : bytes){
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        var encryptedText = builder.toString();
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

    @Override
    public Boolean isAuthorized(String uri, String username) {
        var user = userRepository.findByUsername(username);
        var resource = resourceRepository.findByUri(uri);
        var permissions = permissionRepository.findByRole(user.getRole());
        if (null != resource && null != permissions && !permissions.isEmpty()) {
            for(Permission permission : permissions) {
                if (resource.getId().equals(permission.getResource())) {
                    return true;
                }
            }
        }
        return false;
    }
}
