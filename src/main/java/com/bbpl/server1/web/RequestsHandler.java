package com.bbpl.server1.web;

import com.bbpl.server1.model.Operation;
import com.bbpl.server1.service.IntegrationHandler;
import com.bbpl.server1.service.IntegrationServerException;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestsHandler {

    private final IntegrationHandler integrationHandler;

    public RequestsHandler(IntegrationHandler integrationHandler) {
        this.integrationHandler = integrationHandler;
    }

    @RequestMapping(value = "/doadd", method = RequestMethod.GET)
    public ResponseEntity process(@RequestParam Integer... data) {

        try {
            return ResponseEntity.ok(integrationHandler.exchange(Arrays.asList(data), Operation.ADD));
        }catch (IntegrationServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Communicating with remote service");
        }
    }
}
