package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.Village;

class ConsumptionService {
    private final ConsumptionRepository consumptionRepository;

    ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    void increment(Consumption consumption, Village.Id villageId) {
        this.consumptionRepository.insert(consumption, villageId);
    }
}
