import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:10.11");

    private static void startContainers() {
      Startables.deepStart(Stream.of(mariadb)).join();
    }

    private static Map<String, String> createConnectionConfiguration() {
      return Map.of(
          "spring.datasource.url", mariadb.getJdbcUrl(),
          "spring.datasource.username", mariadb.getUsername(),
          "spring.datasource.password", mariadb.getPassword());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      startContainers();

      ConfigurableEnvironment environment = applicationContext.getEnvironment();
      MapPropertySource testcontainers = new MapPropertySource("testcontainers", (Map) createConnectionConfiguration());
      environment.getPropertySources().addFirst(testcontainers);
    }

  }

}
