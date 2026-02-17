package com.pickdate.bootstrap.functional;

import com.pickdate.bootstrap.domain.Value;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


public interface Option<T> extends Value<T> {

    static <T> Option<T> from(T value) {
        if (value == null) return none();
        return new Some<>(value);
    }

    static <T> Option<T> none() {
        return None.instance();
    }

    T get();

    default boolean isPresent() {
        return this != none();
    }

    default boolean isEmpty() {
        return !isPresent();
    }

    default <A> A ifPresentGetOrElse(Supplier<A> action, Supplier<A> emptyAction) {
        return isPresent() ? action.get() : emptyAction.get();
    }

    default <A> A ifPresentOrElse(Function<T, A> function, Supplier<A> emptyAction) {
        return isPresent() ? function.apply(value()) : emptyAction.get();
    }

    default T orGet(T other) {
        return isPresent() ? value() : other;
    }

    default Stream<T> stream() {
        if (isEmpty()) {
            return Stream.empty();
        } else {
            return Stream.of(value());
        }
    }

    record Some<T>(T value) implements Option<T> {

        @Override
        public T get() {
            return value;
        }

        @Override
        public T value() {
            return get();
        }
    }

    record None<T>() implements Option<T> {

        private static final None<Object> INSTANCE = new None<>();

        @SuppressWarnings("unchecked")
        static <T> None<T> instance() {
            return (None<T>) INSTANCE;
        }

        @Override
        public T get() {
            return null;
        }

        @Override
        public T value() {
            return null;
        }
    }
}
