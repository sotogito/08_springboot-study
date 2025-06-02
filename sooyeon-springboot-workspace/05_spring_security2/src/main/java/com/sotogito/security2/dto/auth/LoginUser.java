package com.sotogito.security2.dto.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoginUser extends User { /*implements UserDetails*/

    private String displayUserName;

    public LoginUser(String username,
                     String password,
                     Collection<? extends GrantedAuthority> authorities,
                     String displayUserName) {

        super(username, password, authorities);
        this.displayUserName = displayUserName;
    }

    public String getDisplayUserName() {
        return displayUserName;
    }

}
