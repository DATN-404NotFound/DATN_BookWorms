package com.poly.DATN_BookWorms.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;
public class FacebookSignInAdapter  implements SignInAdapter {
    @Override
    public String signIn(String s, Connection<?> connection, NativeWebRequest nativeWebRequest) {
        System.out.println(" ====== Sign In adapter");
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        connection.getDisplayName(), null,
                        Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_GUEST"))));

        return null;
    }
}
