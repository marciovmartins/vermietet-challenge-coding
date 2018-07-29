package vermietet.challenge.coding.consumption;

import org.junit.Test;
import vermietet.challenge.coding.village.Village;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IncrementConsumptionTest {
    @Test
    public void testIncrementConsumption() {
        // setup
        ConsumptionRepository consumptionRepository = mock(ConsumptionRepository.class);
        Village.Id villageId = mock(Village.Id.class);
        Consumption consumption = mock(Consumption.class);

        // execution
        IncrementConsumption incrementConsumption = new IncrementConsumption(consumptionRepository);
        incrementConsumption.with(consumption, villageId);

        // assertion
        verify(consumptionRepository).insert(consumption, villageId);
    }
}
