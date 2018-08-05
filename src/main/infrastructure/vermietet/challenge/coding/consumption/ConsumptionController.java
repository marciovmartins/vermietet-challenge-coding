package vermietet.challenge.coding.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class ConsumptionController {
    private final IncrementConsumption incrementConsumption;

    @Autowired
    public ConsumptionController(IncrementConsumption incrementConsumption) {
        this.incrementConsumption = incrementConsumption;
    }

    @PostMapping("/counter_callback")
    public void incrementConsumption(@RequestBody Input input) { // TODO: tests need to be implemented.
        incrementConsumption.with(
                input.getAmount(),
                input.getCounterId()
        );
    }

    static class Input {
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
