package vermietet.challenge.coding.application.village;

import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.domain.village.VillageRepository;

public class GetVillage {
    private final VillageRepository villageRepository;

    public GetVillage(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public Village by(Integer villageId) {
        return villageRepository.findBy(new Village.Id(villageId));
    }
}
