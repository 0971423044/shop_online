package com.example.shoponline.User;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.awt.desktop.SystemSleepEvent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PassEncodeTests {
    @Test

    public void testPassEncode()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "xuanxhaka";

        String encodedPass = passwordEncoder.encode(rawPassword);

        boolean matchPass = passwordEncoder.matches(rawPassword,encodedPass);

        System.out.println("Password after encode: "  + encodedPass);

        assertThat(matchPass).isTrue();
    }
}
