package io.keepup.cms.projects.boot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;

import static io.keepup.cms.projects.service.TestService.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationUnitTest {

    @BeforeAll
    static void beforeAll() {
        UNIT_TEST = true;
    }

    @AfterAll
    static void afterAll() {
        UNIT_TEST = false;
    }

    @Test
    void main() {
        assertThrows(BeanCreationException.class, () -> Application.main());
    }
}