package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@SuppressWarnings("unused")
public class VillageController {
    private final GetVillage getVillage;

    @Autowired
    public VillageController(GetVillage getVillage) {
        this.getVillage = getVillage;
    }

    @GetMapping("/counter")
    public HashMap<String, Object> getVillage(@RequestParam("id") Integer villageId) {
        Village village = getVillage.by(villageId);

        return new HashMap<String, Object>() {{
            put("id", Integer.parseInt(village.id().toString()));
            put("village_name", village.name().toString());
        }};
    }
}
