/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */


package village.energy.consumption.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import village.energy.consumption.dto.PostCounterCallback;
import village.energy.consumption.service.ConsumptionService;

import java.util.Map;

/**
 * @author meenakshi.gupta
 * @version $Id: ConsumptionController.java, v 0.1 2020-02-20 21:07 meenakshi.gupta Exp $$
 */

@RestController
public class ConsumptionController {
    private final ConsumptionService service;

    @Autowired
    public ConsumptionController(ConsumptionService service) {
        this.service = service;
    }

    @GetMapping("/consumption_report")
    public Map<String, Double> getConsumptionReport(@RequestParam("duration") String duration) {
        return service.calculateConsumption(duration);
    }

    @PostMapping("/counter_callback")
    @ResponseStatus(HttpStatus.CREATED)
    public void postConsumptionData(@RequestBody PostCounterCallback counterCallback) {
        service.setConsumption(counterCallback);
    }

}