/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package village.energy.consumption.repository;

/**
 * @author meenakshi.gupta
 * @version $Id: ConsumptionRepository.java, v 0.1 2020-02-20 21:38 meenakshi.gupta Exp $$
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import village.energy.consumption.domain.ConsumptionRecord;

import java.util.Map;

@Repository
public interface ConsumptionRepository extends CrudRepository<ConsumptionRecord, Long> {
    @Query(value = "SELECT SUM(CONSUMPTION_AMOUNT) FROM CONSUMPTION_RECORD WHERE TIMESTAMP > :minusOneDay AND TIMESTAMP< =: currentTime GROUP BY COUNTER_ID;")
    Map<String, Double> getAggregateConsumptionForEachCounter(@Param("minusOneDay") long lastDay, @Param("currentTime") long currentTime);
}