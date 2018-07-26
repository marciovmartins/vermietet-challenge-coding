package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringIncrementConsumption extends IncrementConsumption {
    @Autowired
    public SpringIncrementConsumption(ConsumptionRepository consumptionRepository) {
        super(consumptionRepository);
    }
}