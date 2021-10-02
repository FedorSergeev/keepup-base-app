package io.keepup.cms.projects.service;

import io.keepup.cms.projects.exception.TestServiceException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public static boolean UNIT_TEST;

    public TestService() {
        if (UNIT_TEST) {
            throw new TestServiceException("Exception from TestService");
        }
    }
}
