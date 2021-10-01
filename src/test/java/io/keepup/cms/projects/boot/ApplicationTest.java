package io.keepup.cms.projects.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {

    @Autowired
    ReactiveUserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        assertNotNull(userDetailsService);
    }
}