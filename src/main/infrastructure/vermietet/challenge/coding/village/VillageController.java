package vermietet.challenge.coding.village;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class VillageController {
    private final GetVillage getVillage;

    @Autowired
    public VillageController(GetVillage getVillage) {
        this.getVillage = getVillage;
    }

    @GetMapping("/counter")
    public VillageDTO getVillage(@RequestParam("id") String pVillageId) {
        Village village = getVillage.by(new Village.Id(Integer.parseInt(pVillageId)));

        return new VillageDTO(
                Integer.parseInt(village.id().toString()),
                village.name().toString()
        );
    }

    public static class VillageDTO {
        @JsonProperty("id")
        final private Integer id;
        @JsonProperty("village_name")
        final private String villageName;

        VillageDTO(Integer id, String villageName) {
            this.id = id;
            this.villageName = villageName;
        }

        public Integer getId() {
            return id;
        }

        public String getVillageName() {
            return villageName;
        }
    }
}
