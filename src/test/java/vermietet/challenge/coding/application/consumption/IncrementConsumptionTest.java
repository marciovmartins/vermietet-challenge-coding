package vermietet.challenge.coding.application.consumption;

import org.junit.Test;
import vermietet.challenge.coding.domain.consumption.Consumption;
import vermietet.challenge.coding.domain.consumption.ConsumptionRepository;
import vermietet.challenge.coding.domain.village.Village;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IncrementConsumptionTest {
    @Test
    public void testIncrementConsumption() { // TODO: Is there a way to replace Village.Id and Consumption by mocks?
        // setup
        double consumptionValue = 1.0;
        int villageIdValue = 1;

        ConsumptionRepository consumptionRepository = mock(ConsumptionRepository.class);
        Village.Id villageId = new Village.Id(villageIdValue);
        Consumption consumption = new Consumption(consumptionValue);

        // execution
        IncrementConsumption incrementConsumption = new IncrementConsumption(consumptionRepository);
        incrementConsumption.with(consumptionValue, villageIdValue);

        // assertion
        verify(consumptionRepository).insert(eq(consumption), eq(villageId));
    }
}
