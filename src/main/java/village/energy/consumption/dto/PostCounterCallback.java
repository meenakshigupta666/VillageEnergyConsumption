/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package village.energy.consumption.dto;

/**
 * @author meenakshi.gupta
 * @version $Id: PostCounterCallback.java, v 0.1 2020-02-20 21:17 meenakshi.gupta Exp $$
 */
public class PostCounterCallback {

    private String counterId;
    private Double amount;

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringBuilder("PostCounterCallback{ counterId=" + counterId + "amount=" + amount + "}").toString();
    }
}