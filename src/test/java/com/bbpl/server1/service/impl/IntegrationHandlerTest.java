package com.bbpl.server1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bbpl.server1.model.CalcInput;
import com.bbpl.server1.model.Operation;
import com.bbpl.server1.service.IntegrationHandler;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IntegrationHandlerTest {

    IntegrationHandler handler;

    @Test
    public void exchange() {

        RestTemplate template = mock(RestTemplate.class);

        when(template.postForEntity(anyString(), any(CalcInput.class), eq(CalcInput.class)))
                .thenReturn(new ResponseEntity<CalcInput>(CalcInput.builder().result(6).build(), HttpStatus.OK));
        handler = new IntegrationHandlerImpl("", template);

        Integer result = handler.exchange(Collections.emptyList(), Operation.ADD);
        assertEquals("result must be returned", 6, result.intValue());
    }
}