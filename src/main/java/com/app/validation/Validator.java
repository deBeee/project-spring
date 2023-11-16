package com.app.validation;
@FunctionalInterface
public interface Validator <T>{
    boolean validate(T data);
}
