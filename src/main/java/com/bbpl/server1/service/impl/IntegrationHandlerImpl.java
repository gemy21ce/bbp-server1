package com.bbpl.server1.service.impl;

import com.bbpl.server1.model.CalcInput;
import com.bbpl.server1.model.Operation;
import com.bbpl.server1.service.IntegrationHandler;
import com.bbpl.server1.service.IntegrationServerException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class IntegrationHandlerImpl implements IntegrationHandler {

    private final String calcServerUrl;
    private static final String OPERATION_PATH = "/operation/process";
    private final RestTemplate restTemplate;

    public IntegrationHandlerImpl(@Value("${calc.server.url}") String calcServiceUrl, RestTemplate restTemplate) {
        this.calcServerUrl = calcServiceUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Integer exchange(List<Integer> data, Operation operation) throws IntegrationServerException {
        CalcInput input = CalcInput.builder().data(data).operation(operation).build();

        log.debug("exchanging data: {}", input);

        ResponseEntity<CalcInput> response;
        try {
            response = restTemplate.postForEntity(calcServerUrl + OPERATION_PATH, input, CalcInput.class);
            log.debug("response with status code: {}", response.getStatusCode());
        } catch (Exception e) {
            log.error("error communicating with remote server", e);
            throw new IntegrationServerException();
        }

        return response.getBody().getResult();
    }
}
