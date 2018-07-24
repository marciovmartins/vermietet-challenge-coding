package vermietet.challenge.coding.village;

interface VillageRepository {
    Village findBy(Village.Id villageId);
}
