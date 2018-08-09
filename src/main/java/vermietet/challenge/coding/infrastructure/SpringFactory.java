package vermietet.challenge.coding.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import vermietet.challenge.coding.application.consumption.GetReportConsumption;
import vermietet.challenge.coding.application.consumption.IncrementConsumption;
import vermietet.challenge.coding.application.village.GetVillage;
import vermietet.challenge.coding.domain.Environment;
import vermietet.challenge.coding.domain.consumption.ConsumptionRepository;
import vermietet.challenge.coding.domain.consumption.ReportConsumptionRepository;
import vermietet.challenge.coding.domain.village.VillageRepository;
import vermietet.challenge.coding.infrastructure.consumption.JdbcConsumptionRepository;
import vermietet.challenge.coding.infrastructure.consumption.JdbcReportConsumptionRepository;
import vermietet.challenge.coding.infrastructure.utils.jdbc.JdbcConnection;
import vermietet.challenge.coding.infrastructure.village.JdbcVillageRepository;

@Component
public class SpringFactory {
    @Bean
    @Autowired
    public GetVillage buildGetVillage(VillageRepository villageRepository) {
        return new GetVillage(villageRepository);
    }

    @Bean
    @Autowired
    public VillageRepository buildVillageRepository(JdbcConnection jdbcConnection) {
        return new JdbcVillageRepository(jdbcConnection);
    }

    @Bean
    @Autowired
    public JdbcConnection buildJdbcConnection(Environment environment) {
        return new JdbcConnection(environment);
    }

    @Bean
    public Environment buildEnvironment() {
        return new LocalEnvironment();
    }

    @Bean
    @Autowired
    public IncrementConsumption buildIncrementConsumption(ConsumptionRepository consumptionRepository) {
        return new IncrementConsumption(consumptionRepository);
    }

    @Bean
    @Autowired
    public ConsumptionRepository buildConsumptionRepository(JdbcConnection jdbcConnection) {
        return new JdbcConsumptionRepository(jdbcConnection);
    }

    @Bean
    @Autowired
    public GetReportConsumption buildGetReportConsumption(
            ReportConsumptionRepository reportConsumptionRepository,
            VillageRepository villageRepository
    ) {
        return new GetReportConsumption(reportConsumptionRepository, villageRepository);
    }

    @Bean
    @Autowired
    public ReportConsumptionRepository buildReportConsumptionRepository(JdbcConnection jdbcConnection) {
        return new JdbcReportConsumptionRepository(jdbcConnection);
    }
}
