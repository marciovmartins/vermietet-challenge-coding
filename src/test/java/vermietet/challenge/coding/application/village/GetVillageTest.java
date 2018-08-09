package vermietet.challenge.coding.application.village;

import org.junit.Test;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.domain.village.VillageRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetVillageTest {
    @Test
    public void testGetVillageById() {
        // setup
        Village.Id villageId = new Village.Id(1);
        Village village = mock(Village.class);

        VillageRepository villageRepository = mock(VillageRepository.class);
        when(villageRepository.findBy(eq(villageId))).thenReturn(village);

        // execution
        GetVillage getVillage = new GetVillage(villageRepository);
        Village returnedVillage = getVillage.by(1);

        // assertions
        assertEquals(village, returnedVillage);
    }
}