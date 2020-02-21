/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package village.energy.consumption.domain;

/**
 * @author meenakshi.gupta
 * @version $Id: ConsumptionRecord.java, v 0.1 2020-02-20 22:04 meenakshi.gupta Exp $$
 */

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Table
@Entity
public class ConsumptionRecord {

    @Id
    @GeneratedValue
    private long id;

    private String counterId;
    private double consumptionAmount;
    private long timestamp;

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public double getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(double consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new StringBuilder("ConsumptionRecord{id=" + id + "counterId=" + counterId + "timeStamp=" + timestamp + "}").toString();
    }

}