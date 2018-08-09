package vermietet.challenge.coding.domain.consumption;

import vermietet.challenge.coding.domain.ValueObject;
import vermietet.challenge.coding.domain.village.Village;

public class ReportConsumption extends ValueObject {
    private final Consumption consumption;
    private final Village.Name villageName;

    public ReportConsumption(Consumption consumption, Village.Name villageName) {
        super(villageName.toString() + "=" + consumption.toString());
        this.consumption = consumption;
        this.villageName = villageName;
    }

    public Village.Name villageName() {
        return villageName;
    }

    public Consumption consumption() {
        return consumption;
    }
}
