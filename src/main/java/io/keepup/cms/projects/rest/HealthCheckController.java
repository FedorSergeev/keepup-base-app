package io.keepup.cms.projects.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Simple controller with endpoint for controlling application health
 *
 * @author Fedor Sergeev
 */
@RestController
@RequestMapping("/health")
public class HealthCheckController {

    private final Log log = LogFactory.getLog(getClass());

    /**
     * Just a simple endpoint for checking whether application responses.
     *
     * @return OK 200
     */
    @GetMapping
    public Mono<ResponseEntity<String>> health() {
        log.debug("Health check requested");
        return Mono.just(ResponseEntity.ok("OK"));
    }
}
