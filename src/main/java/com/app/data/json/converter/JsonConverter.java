package com.app.data.json.converter;

import java.io.FileReader;
import java.io.FileWriter;

public interface JsonConverter<T> {
    void toJson(T data, FileWriter writer);
    T fromJson(FileReader reader, Class<T> tClass);
}
