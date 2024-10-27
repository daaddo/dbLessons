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
@Order(3)
public class ProductionInizializerDb implements CommandLineRunner {
    private final DataSource dataSource;
    private final CountDownLatch latch;

    @Autowired
    public ProductionInizializerDb(CountDownLatch latch,DataSource dataSource) {
        this.dataSource = dataSource;
        this.latch = latch;
    }
    @Override
    public void run(String... args) throws Exception {

        Resource resource = new ClassPathResource("script3.sql");
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, resource);
            System.out.println("[ALERT] Script production eseguito con successo");
        }catch (Exception e){
            System.out.println("[ALERT] Errore durante l'esecuzione dello script production");
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
