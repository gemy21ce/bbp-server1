package com.bbpl.server1.service;

import com.bbpl.server1.model.Operation;
import java.util.List;

public interface IntegrationHandler {

    Integer exchange(List<Integer> data, Operation operation) throws IntegrationServerException;
}
