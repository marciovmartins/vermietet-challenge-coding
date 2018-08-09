package vermietet.challenge.coding.village;

public class GetVillage {
    private final VillageRepository villageRepository;

    public GetVillage(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    Village by(Integer villageId) {
        return villageRepository.findBy(new Village.Id(villageId));
    }
}
