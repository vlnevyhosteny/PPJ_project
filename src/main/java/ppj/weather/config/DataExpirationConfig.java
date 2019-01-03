package ppj.weather.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import ppj.weather.Main;
import ppj.weather.model.WeatherRecord;

@Configuration
public class DataExpirationConfig implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DataExpirationConfig.class);

    private final WeatherDataConfig weatherDataConfig;

    private final MongoTemplate mongoTemplate;

    public DataExpirationConfig(WeatherDataConfig weatherDataConfig, MongoTemplate mongoTemplate) {
        this.weatherDataConfig = weatherDataConfig;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("Expiration of data is set to " + weatherDataConfig.getExpiration() + "s");

        try {
            setExpirationOnDateIndex();
        } catch(Exception ex) {
            mongoTemplate.indexOps(WeatherRecord.COLLECTION_NAME).dropIndex(WeatherRecord.DATE_NAME);

            setExpirationOnDateIndex();
        }

    }

    private void setExpirationOnDateIndex() {
        mongoTemplate.indexOps(WeatherRecord.COLLECTION_NAME)
                .ensureIndex(
                        new Index().on(WeatherRecord.DATE_NAME, Sort.Direction.ASC)
                                .expire(weatherDataConfig.getExpiration())
                                .named(WeatherRecord.DATE_NAME)
                );
    }
}
