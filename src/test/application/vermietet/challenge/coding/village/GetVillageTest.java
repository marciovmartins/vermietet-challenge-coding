package vermietet.challenge.coding.village;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetVillageTest {
    @Test
    public void testGetVillageById() {
        // setup
        Village.Id villageId = new Village.Id(1);
        Village village = mock(Village.class);

        VillageRepository villageRepository = mock(VillageRepository.class);
        when(villageRepository.findBy(villageId)).thenReturn(village);

        // execution
        GetVillage getVillage = new GetVillage(villageRepository);
        Village returnedVillage = getVillage.by(villageId);

        // assertions
        assertEquals(village, returnedVillage);
    }
}