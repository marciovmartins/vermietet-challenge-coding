package vermietet.challenge.coding.village;

public class VillageService {
    private final VillageRepository villageRepository;

    VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    Village getBy(Village.Id villageId) {
        return this.villageRepository.findBy(villageId);
    }
}
