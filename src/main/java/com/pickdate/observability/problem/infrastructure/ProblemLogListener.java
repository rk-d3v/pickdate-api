package com.pickdate.observability.problem.infrastructure;

import com.pickdate.observability.problem.application.ProblemLogUseCase;
import com.pickdate.shared.exception.ProblemCapturedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
class ProblemLogListener {

    private final ProblemLogUseCase problemLogUseCase;

    @ApplicationModuleListener
    public void onProblemEvent(ProblemCapturedEvent problemEvent) {
        log.info("Handling problem event: {}", problemEvent);
        var entity = ProblemLogEventMapper.toEntity(problemEvent);
        problemLogUseCase.save(entity);
    }
}

