package vermietet.challenge.coding.domain.consumption;

import vermietet.challenge.coding.domain.village.Village;

public interface ConsumptionRepository {
    void insert(Consumption consumption, Village.Id villageId);
}
