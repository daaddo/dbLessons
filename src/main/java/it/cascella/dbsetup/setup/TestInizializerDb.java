package it.cascella.dbsetup.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

@Component
@Order(2)
@Profile("test")
public class TestInizializerDb implements CommandLineRunner {
    private final DataSource dataSource;
    private CountDownLatch latch;
    @Autowired
    public TestInizializerDb(CountDownLatch latch, DataSource dataSource) {
        this.dataSource = dataSource;
        this.latch = latch;
    }
    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("script_test.sql");
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, resource);
            System.out.println("[ALERT] Script test eseguito con successo");
        }catch (Exception e) {
            System.out.println("[ALERT] Errore durante l'esecuzione dello script test");
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
