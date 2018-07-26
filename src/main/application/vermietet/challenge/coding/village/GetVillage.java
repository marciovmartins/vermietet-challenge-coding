package vermietet.challenge.coding.village;

public class GetVillage {
    private final VillageRepository villageRepository;

    GetVillage(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    Village by(Village.Id villageId) {
        return this.villageRepository.findBy(villageId);
    }
}
