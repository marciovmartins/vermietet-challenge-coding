package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.Village;

public interface ConsumptionRepository {
    void insert(Consumption consumption, Village.Id villageId);
}
