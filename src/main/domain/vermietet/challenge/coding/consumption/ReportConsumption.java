package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.ValueObject;
import vermietet.challenge.coding.village.Village;

class ReportConsumption extends ValueObject {
    private final Consumption consumption;
    private final Village.Name villageName;

    ReportConsumption(Consumption consumption, Village.Name villageName) {
        super(villageName.toString() + "=" + consumption.toString());
        this.consumption = consumption;
        this.villageName = villageName;
    }

    Village.Name villageName() {
        return villageName;
    }

    Consumption consumption() {
        return consumption;
    }
}
