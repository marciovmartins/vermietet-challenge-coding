package vermietet.challenge.coding.village;

public class GetVillage {
    private final VillageRepository villageRepository;

    GetVillage(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    Village by(Integer pVillageId) {
        Village.Id villageId = new Village.Id(pVillageId);
        return villageRepository.findBy(villageId);
    }
}
