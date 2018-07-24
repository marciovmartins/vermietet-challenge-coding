package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultVillageService implements VillageService {
    private final VillageRepository villageRepository;

    @Autowired
    DefaultVillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    @Override
    public Village getBy(Village.Id villageId) {
        return this.villageRepository.findBy(villageId);
    }
}
