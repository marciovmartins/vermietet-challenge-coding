package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

public class ConsumptionService {
    private final ConsumptionRepository consumptionRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    void increment(Consumption consumption, Village.Id villageId) {
        this.consumptionRepository.insert(consumption, villageId);
    }
}
