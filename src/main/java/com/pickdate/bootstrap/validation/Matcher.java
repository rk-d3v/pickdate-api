package com.pickdate.bootstrap.validation;


public interface Matcher<T> {

    boolean matches(T value);
}

