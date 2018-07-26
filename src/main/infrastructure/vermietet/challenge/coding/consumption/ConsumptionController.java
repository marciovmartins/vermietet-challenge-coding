package vermietet.challenge.coding.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vermietet.challenge.coding.village.Village;

@RestController
public class ConsumptionController {
    private final IncrementConsumption incrementConsumption;

    @Autowired
    public ConsumptionController(IncrementConsumption incrementConsumption) {
        this.incrementConsumption = incrementConsumption;
    }

    @PostMapping("/counter_callback")
    public void incrementConsumption(@RequestBody ConsumptionDTO input) { // TODO: tests need to be implemented.
        Consumption consumption = new Consumption(input.getAmount());
        Village.Id villageId = new Village.Id(input.getCounterId());

        this.incrementConsumption.with(consumption, villageId);
    }

    static class ConsumptionDTO {
        @JsonProperty("counter_id")
        private Integer counterId;
        @JsonProperty("amount")
        private Double amount;

        Integer getCounterId() {
            return counterId;
        }

        Double getAmount() {
            return amount;
        }
    }
}
