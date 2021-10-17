package io.keepup.cms.projects.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
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
     * This is an imitation of long process, will be used for load testing
     *
     * @return OK 200 or error message if there were problems with th thread
     */
    @GetMapping
    public Mono<ResponseEntity<String>> health() {
        log.debug("Health check requested");
        return getResponseEntityMono()
                .map(ResponseEntity::ok);
    }

    @Nullable
    private Mono<String> getResponseEntityMono() {
        return WebClient.create("http://mock-server:8080/delay?seconds=1")
                .get()
                .retrieve()
                .bodyToMono(String.class);

    }
}
