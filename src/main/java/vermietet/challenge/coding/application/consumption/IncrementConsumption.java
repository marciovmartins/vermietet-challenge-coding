package vermietet.challenge.coding.application.consumption;

import vermietet.challenge.coding.domain.consumption.Consumption;
import vermietet.challenge.coding.domain.consumption.ConsumptionRepository;
import vermietet.challenge.coding.domain.village.Village;

public class IncrementConsumption {
    private final ConsumptionRepository consumptionRepository;

    public IncrementConsumption(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public void with(Double consumption, Integer villageId) {
        consumptionRepository.insert(
                new Consumption(consumption),
                new Village.Id(villageId)
        );
    }
}
