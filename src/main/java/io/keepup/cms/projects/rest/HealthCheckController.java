package io.keepup.cms.projects.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

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
        ConnectionProvider connectionProvider = ConnectionProvider.builder("LoadTestingConnectionPool")
                .maxConnections(10000)
                .pendingAcquireMaxCount(10000)
                .build();
        ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(HttpClient.create(connectionProvider));
        return WebClient.builder()
                .baseUrl("http://192.168.10.147:30083/delay?seconds=1")
                .clientConnector(clientHttpConnector)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
}
