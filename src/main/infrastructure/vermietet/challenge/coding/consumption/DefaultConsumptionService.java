package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermietet.challenge.coding.village.Village;

@Service
public class DefaultConsumptionService implements ConsumptionService {
    private final ConsumptionRepository consumptionRepository;

    @Autowired
    public DefaultConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public void increment(Consumption consumption, Village.Id villageId) {
        this.consumptionRepository.insert(consumption, villageId);
    }
}