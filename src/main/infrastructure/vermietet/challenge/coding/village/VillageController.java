package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VillageController {
    private final VillageService villageService;

    @Autowired
    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @GetMapping("/counter")
    public VillageDTO getVillage(@RequestParam("id") String pVillageId) {
        Village village = this.villageService.getBy(new Village.Id(Integer.parseInt(pVillageId)));

        return new VillageDTO(
                Integer.parseInt(village.id().toString()),
                village.name().toString()
        );
    }
}
