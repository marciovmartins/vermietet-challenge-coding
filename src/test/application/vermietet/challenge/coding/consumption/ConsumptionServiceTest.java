package vermietet.challenge.coding.consumption;

import org.junit.jupiter.api.Test;
import vermietet.challenge.coding.Village;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsumptionServiceTest {
    @Test
    void testIncrementConsumption() {
        // setup
        ConsumptionRepository consumptionRepository = mock(ConsumptionRepository.class);
        Village.Id villageId = mock(Village.Id.class);
        Consumption consumption = mock(Consumption.class);

        // execution
        ConsumptionService consumptionService = new ConsumptionService(consumptionRepository);
        consumptionService.increment(consumption, villageId);

        // assertion
        verify(consumptionRepository).insert(consumption, villageId);
    }
}
