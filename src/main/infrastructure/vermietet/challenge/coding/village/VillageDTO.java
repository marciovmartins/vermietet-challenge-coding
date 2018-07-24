package vermietet.challenge.coding.village;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VillageDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("village_name")
    private String villageName;

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
