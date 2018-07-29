package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

public class IncrementConsumption {
    private final ConsumptionRepository consumptionRepository;

    public IncrementConsumption(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    void with(Consumption consumption, Village.Id villageId) {
        consumptionRepository.insert(consumption, villageId);
    }
}
