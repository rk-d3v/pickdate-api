package com.pickdate.ops.infrastructure;

import com.pickdate.bootstrap.domain.Identifier;
import com.pickdate.bootstrap.exception.Problem;
import com.pickdate.bootstrap.exception.ProblemCapturedEvent;
import com.pickdate.bootstrap.exception.ProblemDetailsForAdminView;
import com.pickdate.bootstrap.exception.ProblemDetailsWithParameters;
import com.pickdate.ops.domain.ProblemEntity;


final class ProblemEventMapper {

    private ProblemEventMapper() {
    }

    static ProblemEntity toEntity(ProblemCapturedEvent problemEvent) {
        var problem = problemEvent.problem();
        var entity = new ProblemEntity()
                .withId(Identifier.of(problem.getTraceId()))
                .withStatus(problem.getStatus())
                .withDetail(problem.getDetail())
                .withInstance(problem.getInstance())
                .withStackTrace(problemEvent.stackTrace())
                .withTitle(problem.getTitle());

        if (problem instanceof ProblemDetailsWithParameters params) {
            return entity.withInvalidParams(params.getInvalidParams());
        }

        return entity;
    }

    static Problem toProblem(ProblemEntity entity) {
        return ProblemDetailsForAdminView.builder()
                .title(entity.getTitle())
                .status(entity.getStatus())
                .detail(entity.getDetail())
                .instance(entity.getInstance())
                .traceId(entity.getId().value())
                .stackTrace(entity.getStackTrace())
                .invalidParams(entity.getInvalidParams())
                .build();
    }
}

