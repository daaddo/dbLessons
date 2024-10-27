package it.cascella.dbsetup.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;


//Questa classe gestisce l'inizializzazione del database ovvero fa partire lo script script_base.sql prima di qualunque altro script sql
@Component
@Order(1)
public class InitializerDb implements CommandLineRunner {
    private final CountDownLatch latch;
    private final DataSource dataSource;

    @Autowired
    public InitializerDb(CountDownLatch countDownLatch, DataSource dataSource) {
        this.dataSource = dataSource;
        latch = countDownLatch;
    }
    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("script_base.sql");
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, resource);
            System.out.println("[ALERT] Script base eseguito con successo");
        }catch (Exception e){
            System.out.println("[ALERT] Errore durante l'esecuzione dello script base");
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
