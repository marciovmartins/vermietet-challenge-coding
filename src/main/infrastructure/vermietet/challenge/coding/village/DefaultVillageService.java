package vermietet.challenge.coding.village;

public class DefaultVillageService implements VillageService {
    private final VillageRepository villageRepository;

    DefaultVillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    @Override
    public Village getBy(Village.Id villageId) {
        return this.villageRepository.findBy(villageId);
    }
}
