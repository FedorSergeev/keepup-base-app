package io.keepup.cms.projects.rest;

import io.keepup.cms.core.service.EntityOperationServiceBase;
import io.keepup.cms.projects.model.TestLoadEntity;
import io.keepup.cms.rest.controller.AbstractRestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("load-testing")
public class LoadTestingController extends AbstractRestController<TestLoadEntity> {

    private final Log log = LogFactory.getLog(getClass());

    protected LoadTestingController(EntityOperationServiceBase<TestLoadEntity> operationService) {
        super(operationService);
    }

    /**
     * Saves 10 elements
     *
     * @return OK word signaling that the request has been performed successfully
     */
    @GetMapping("pretest")
    public Mono<ResponseEntity<String>> pretest() {
        List<TestLoadEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var testLoadEntity = new TestLoadEntity();
            testLoadEntity.setListValue(Arrays.asList("a", "b", "c"));
            testLoadEntity.setStringValue(new Date().toString());
            entities.add(testLoadEntity);
        }
        return Flux.fromIterable(entities)
                .flatMap(entity -> operationService.save(entity, 0L))
                .map(saved -> {
                    log.info("Entity saved: id = %d, date = %s".formatted(saved.getId(), saved.getStringValue()));
                    return saved;
                })
                .collectList()
                .thenReturn(ResponseEntity.ok("OK"));
    }
}
