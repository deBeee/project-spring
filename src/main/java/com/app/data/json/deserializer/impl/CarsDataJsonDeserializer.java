package com.app.data.json.deserializer.impl;


import com.app.data.json.converter.JsonConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.json.deserializer.generic.AbstractJsonDeserializer;
import com.app.data.model.CarData;
import com.app.data.model.CarsData;

public class CarsDataJsonDeserializer extends AbstractJsonDeserializer<CarsData> implements JsonDeserializer<CarsData> {
    public CarsDataJsonDeserializer(JsonConverter<CarsData> converter) {
        super(converter);
    }
}
