package com.pickdate.ops.infrastructure;

import com.pickdate.bootstrap.exception.ProblemCapturedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
class ProblemListener {

    private final ProblemService problemService;

    public ProblemListener(ProblemService problemService) {
        this.problemService = problemService;
    }

    @ApplicationModuleListener
    public void handleProblemEvent(ProblemCapturedEvent problemEvent) {
        log.info("Handling problem event: {}", problemEvent);
        var entity = ProblemEventMapper.toEntity(problemEvent);
        problemService.save(entity);
    }
}

