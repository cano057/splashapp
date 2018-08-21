package com.Connection;

import com.ecommerce.model.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientClass {

    private static JwtUser user;

    public ClientClass(JwtUser user) {
        this.user = user;
    }
}
