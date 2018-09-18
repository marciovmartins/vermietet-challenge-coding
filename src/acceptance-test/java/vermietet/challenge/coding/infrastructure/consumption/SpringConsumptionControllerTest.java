package vermietet.challenge.coding.infrastructure.consumption;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import vermietet.challenge.coding.infrastructure.utils.JdbcTest;

import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

public class SpringConsumptionControllerTest extends JdbcTest {
    @Test
    public void testInsertConsumptionAndGetConsumptionReport() throws Exception { // TODO: do not touch the database.
        // setup
        String baseUrl = System.getenv("BASE_URL"); // TODO: extract to external class.
        if (baseUrl == null) baseUrl = "http://app:8080";

        int villageId = 1;
        double consumption = 2.1;
        String villageName = "Stella de audax";

        PreparedStatement stmt = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt.setInt(1, villageId);
        stmt.setString(2, villageName);
        stmt.execute();

        JSONObject consumptionBody = new JSONObject();
        consumptionBody.put("counter_id", villageId);
        consumptionBody.put("amount", consumption);

        JSONObject consumptionReportJson = new JSONObject();
        consumptionReportJson.put("village_name", villageName);
        consumptionReportJson.put("consumption", consumption);
        JSONArray expectedJson = new JSONArray();
        expectedJson.put(consumptionReportJson);

        // execution
        Request.Post(baseUrl + "/counter_callback")
                .bodyString(consumptionBody.toString(), ContentType.APPLICATION_JSON)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        String reportResult = Request.Get(baseUrl + "/consumption_report?duration=1h")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        JSONArray reportJson = new JSONArray(reportResult);

        // assertions
        assertEquals(expectedJson.toString(), reportJson.toString());
    }

    @Override
    protected String[] getTables() {
        return new String[]{"villages", "consumptions"};
    }
}
