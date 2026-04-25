package com.pickdate.shared.exception;


public record ProblemCapturedEvent(
        Problem problem,
        String stackTrace
) {
}
