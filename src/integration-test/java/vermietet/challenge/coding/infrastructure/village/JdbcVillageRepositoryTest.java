package vermietet.challenge.coding.infrastructure.village;

import org.junit.Test;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.infrastructure.utils.JdbcTest;

import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcVillageRepositoryTest extends JdbcTest {
    @Test
    public void testGetVillageById() throws Exception {
        // setup
        Village.Id villageId = new Village.Id(1);
        Village.Name villageName = new Village.Name("Villarriba");

        PreparedStatement stmt1 = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt1.setInt(1, Integer.parseInt(villageId.toString()));
        stmt1.setString(2, villageName.toString());
        stmt1.execute();

        // execution
        JdbcVillageRepository repository = new JdbcVillageRepository(connection);
        Village village = repository.findBy(villageId);

        // assertions
        assertNotNull(village);
        assertEquals(villageId, village.id());
        assertEquals(villageName, village.name());
    }

    @Test
    public void testGetAll() throws Exception {
        // setup
        Village.Id[] villagesId = new Village.Id[]{new Village.Id(1), new Village.Id(2)};
        Village.Name[] villagesName = new Village.Name[]{new Village.Name("Villarriba"), new Village.Name("Villabajo")};

        PreparedStatement stmt1 = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt1.setInt(1, Integer.parseInt(villagesId[0].toString()));
        stmt1.setString(2, villagesName[0].toString());
        stmt1.execute();

        PreparedStatement stmt2 = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt2.setInt(1, Integer.parseInt(villagesId[1].toString()));
        stmt2.setString(2, villagesName[1].toString());
        stmt2.execute();

        // execution
        JdbcVillageRepository repository = new JdbcVillageRepository(connection);
        List<Village> villages = repository.all();

        // assertions
        assertEquals(2, villages.size());

        Village village1 = villages.get(0);
        assertNotNull(village1);
        assertEquals(villagesId[0], village1.id());
        assertEquals(villagesName[0], village1.name());

        Village village2 = villages.get(1);
        assertNotNull(village2);
        assertEquals(villagesId[1], village2.id());
        assertEquals(villagesName[1], village2.name());
    }

    @Override
    protected String[] getTables() {
        return new String[]{"villages"};
    }
}
