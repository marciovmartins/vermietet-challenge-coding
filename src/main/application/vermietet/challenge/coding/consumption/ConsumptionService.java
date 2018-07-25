package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

interface ConsumptionService {
    void increment(Consumption consumption, Village.Id villageId); // TODO: change to Command InsertConsumption
}
