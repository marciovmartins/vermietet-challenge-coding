package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

class ReportConsumption {
    private final Consumption consumption;
    private final Village.Name villageName;

    ReportConsumption(Consumption consumption, Village.Name villageName) {
        this.consumption = consumption;
        this.villageName = villageName;
    }

    Village.Name villageName() {
        return this.villageName;
    }

    Consumption consumption() {
        return this.consumption;
    }
}
