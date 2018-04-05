package ppj.weather.provisioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import ppj.weather.Main;

import javax.sql.DataSource;
import java.util.List;

public class Provisioner {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    private DataSource dataSource;

    @Value("${provisioner.createScriptClassPath}")
    private String createScriptClassPath;

    public void doProvision() {

        List<String> allTables;

        allTables = namedParameterJdbcOperations.getJdbcOperations()
                .queryForList("SELECT TABLE_NAME FROM  INFORMATION_SCHEMA.TABLES", String.class);

        if (!allTables.contains("STATES")) {
            log.warn("DB Provisioner: No tables exist and will be created");

            createDb();

            allTables = namedParameterJdbcOperations.getJdbcOperations()
                    .queryForList("SELECT TABLE_NAME FROM  INFORMATION_SCHEMA.TABLES", String.class);

            System.out.println(allTables);
        } else {
            log.info("DB Provisioner: Table STATES exists, all existing tables: " + allTables);
        }
    }

    public void createDb() {
        Resource rc = new ClassPathResource(createScriptClassPath);

        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
