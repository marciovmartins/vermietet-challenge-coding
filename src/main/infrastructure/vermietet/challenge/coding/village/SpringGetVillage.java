package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringGetVillage extends GetVillage {
    @Autowired
    SpringGetVillage(VillageRepository villageRepository) {
        super(villageRepository);
    }
}
