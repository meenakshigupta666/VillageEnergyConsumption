/**
 * Consumption Service(villageeneryconsumption)
 */
package village.energy.consumption.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import village.energy.consumption.cache.VillageDataCache;
import village.energy.consumption.domain.ConsumptionRecord;
import village.energy.consumption.dto.PostCounterCallback;
import village.energy.consumption.dto.Village;
import village.energy.consumption.repository.ConsumptionRepository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author meenakshi.gupta
 * @version $Id: ConsumptionService.java, v 0.1 2020-02-20 21:35 meenakshi.gupta Exp $$
 */

@Service
public class ConsumptionService {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumptionService.class);

    private final ConsumptionRepository consumptionRepository;

    public void setConsumption(PostCounterCallback counterCallback) {
        ConsumptionRecord ConsumptionRecord = new ConsumptionRecord();
        ConsumptionRecord.setCounterId(counterCallback.getCounterId());
        ConsumptionRecord.setConsumptionAmount(counterCallback.getAmount());
        ConsumptionRecord.setTimestamp(System.currentTimeMillis());
        consumptionRepository.save(ConsumptionRecord);
    }


    public Map<String, Double> calculateConsumption(String duration) {
        Map<String, Double> consumptionPerCounter = calculateConsumptionPerCounter(duration);
        return calculateTotalConsumptionForVillages(consumptionPerCounter);
    }

    private Map<String, Double> calculateConsumptionPerCounter(String duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -Integer.parseInt(duration));
        long before = calendar.getTimeInMillis();
        Map<String, Double> lat24HrsConsumptionData = consumptionRepository.getAggregateConsumptionForEachCounter(before, System.currentTimeMillis());
        return lat24HrsConsumptionData;
    }


    public Map<String, Double> calculateTotalConsumptionForVillages(Map<String, Double> consumptionPerCounter) {

        Map<String, Double> finalConsumptionPerVillageWithVillageName = new HashMap<String, Double>();

        VillageDataCache villageDataCache = new VillageDataCache();
        Map<String, Village> villageData = villageDataCache.getVillageData();

        Map<String, Double> consumptionPerVillage = new HashMap<String, Double>();
        Set<String> counterIds = consumptionPerCounter.keySet();
        Iterator<String> iterator = counterIds.iterator();
        while (iterator.hasNext()) {
            String counterId = iterator.next();
            finalConsumptionPerVillageWithVillageName.put(villageData.get(counterId).getName(), consumptionPerCounter.get(counterId));
        }
        return consumptionPerVillage;
    }

    @Autowired
    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }
}