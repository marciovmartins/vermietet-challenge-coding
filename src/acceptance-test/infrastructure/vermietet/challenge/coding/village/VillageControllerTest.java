package vermietet.challenge.coding.village;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;
import org.junit.Test;
import vermietet.challenge.coding.utils.JdbcTest;

import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

public class VillageControllerTest extends JdbcTest {
    @Test
    public void getVillageById() throws Exception { // TODO: do not touch the database.
        // setup
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("id", 1);
        expectedJson.put("village_name", "Villarriba");

        PreparedStatement stmt = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt.setInt(1, expectedJson.getInt("id"));
        stmt.setString(2, expectedJson.getString("village_name"));
        stmt.execute();

        // execution
        String result = Request.Get("http://localhost:13002/counter?id=1")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        JSONObject resultJson = new JSONObject(result);

        // assertion
        assertEquals(expectedJson.toString(), resultJson.toString());
    }

    @Override
    protected String[] getTables() {
        return new String[]{"villages"};
    }
}
