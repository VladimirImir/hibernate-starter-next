package com.dev.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
