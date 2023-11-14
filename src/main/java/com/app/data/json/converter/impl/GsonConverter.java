package com.app.data.json.converter.impl;

import com.app.data.json.converter.JsonConverter;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;

@RequiredArgsConstructor
public class GsonConverter<T> implements JsonConverter<T> {
    private final Gson gson;

    @Override
    public void toJson(T data, FileWriter writer) {
        gson.toJson(data, writer);
    }

    @Override
    public T fromJson(FileReader reader, Class<T> tClass) {
        return gson.fromJson(reader, tClass);
    }
}
