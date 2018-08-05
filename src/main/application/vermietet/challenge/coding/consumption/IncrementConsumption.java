package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

public class IncrementConsumption {
    private final ConsumptionRepository consumptionRepository;

    public IncrementConsumption(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    void with(Double consumption, Integer villageId) {
        consumptionRepository.insert(
                new Consumption(consumption),
                new Village.Id(villageId)
        );
    }
}
