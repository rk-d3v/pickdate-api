package com.pickdate.bootstrap.exception;


public record ProblemCapturedEvent(
        Problem problem,
        String stackTrace
) {
}
