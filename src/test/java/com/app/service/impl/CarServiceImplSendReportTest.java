package com.app.service.impl;

import com.app.repository.CarRepository;
import com.app.service.EmailService;
import com.app.service.HtmlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.app.Cars.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplSendReportTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private HtmlService htmlService;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void beforeEach() {
        var CARS = List.of(BMW_CAR, AUDI_1_CAR, AUDI_2_CAR);
        when(carRepository.getCars())
                .thenReturn(CARS);
    }

    @Test
    @DisplayName("when report has been sent correctly")
    void test1(){
        doNothing()
                .when(emailService)
                .send(anyString(), anyString(), anyString());

        when(htmlService.toHtml(anyString(), any()))
                .thenReturn("");

        when(htmlService.manyToHtml(anyString(), anyList()))
                .thenReturn("");

        when(htmlService.pairsToHtml(anyString(), anyMap()))
                .thenReturn("");

        Assertions.assertDoesNotThrow(() ->  carService.sendReport("",""));

        verify(emailService, times(1))
                .send(anyString(), anyString(), anyString());

        verify(htmlService, times(1))
                .toHtml(anyString(), any());

        verify(htmlService, times(5))
                .manyToHtml(anyString(), anyList());

        verify(htmlService, times(5))
                .pairsToHtml(anyString(), anyMap());
    }

}
