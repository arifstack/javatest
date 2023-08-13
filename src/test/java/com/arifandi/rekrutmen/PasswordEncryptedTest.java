package com.arifandi.rekrutmen;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("default")
@Slf4j
public class PasswordEncryptedTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encryptPassword() {
        log.info("password {}", passwordEncoder.encode("test123"));
    }
}
