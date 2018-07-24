package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.Village;

interface ConsumptionService { // TODO: change to Command InsertConsumption
    void increment(Consumption consumption, Village.Id villageId);
}
