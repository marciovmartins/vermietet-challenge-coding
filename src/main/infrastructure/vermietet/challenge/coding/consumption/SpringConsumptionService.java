package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringConsumptionService extends ConsumptionService {
    @Autowired
    public SpringConsumptionService(ConsumptionRepository consumptionRepository) {
        super(consumptionRepository);
    }
}