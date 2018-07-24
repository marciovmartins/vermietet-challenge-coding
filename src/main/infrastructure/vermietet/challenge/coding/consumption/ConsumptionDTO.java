package vermietet.challenge.coding.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;

class ConsumptionDTO {
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
