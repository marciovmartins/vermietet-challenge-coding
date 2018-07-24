package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

public interface ConsumptionRepository {
    void insert(Consumption consumption, Village.Id villageId);
}
