package vermietet.challenge.coding.domain.village;

import java.util.List;

public interface VillageRepository {
    Village findBy(Village.Id villageId);

    List<Village> all();
}
