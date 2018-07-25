package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringVillageService extends VillageService {
    @Autowired
    SpringVillageService(VillageRepository villageRepository) {
        super(villageRepository);
    }
}
