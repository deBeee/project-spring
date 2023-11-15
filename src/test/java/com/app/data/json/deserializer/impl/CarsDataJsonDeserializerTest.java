package com.app.data.json.deserializer.impl;

import com.app.config.AppTestBeansConfig;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarsData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
public class CarsDataJsonDeserializerTest {
    @Autowired
    private JsonDeserializer<CarsData> jsonDeserializer;

    @Test
    @DisplayName("when data is loaded correctly")
    void test1() {
        var path = Paths
                .get("src", "test", "resources", "cars-test.json")
                .toFile()
                .getAbsolutePath();
        var cars = jsonDeserializer.fromJson(path).cars();
        assertThat(cars).hasSize(2);
    }
}
