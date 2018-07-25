package vermietet.challenge.coding.village;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VillageServiceTest {
    @Test
    void testGetVillageById() {
        // setup
        Village.Id villageId = new Village.Id(1);
        Village village = mock(Village.class);

        VillageRepository villageRepository = mock(VillageRepository.class);
        when(villageRepository.findBy(villageId)).thenReturn(village);

        // execution
        VillageService villageService = new VillageService(villageRepository);
        Village returnedVillage = villageService.getBy(villageId);

        // assertions
        assertEquals(village, returnedVillage);
    }
}