package com.shamanthaka.rl.service;

import com.shamanthaka.rl.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@Testcontainers
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    /*@Container
    private static MySQLContainer container = new MySQLContainer("mysql:latest")
            .withDatabaseName("my_prod_db")
            .withUsername("root")
            .withPassword("root");*/

    /*@Container
    private static MySQLContainer container = new MySQLContainer("mysql:latest");*/

    //@Container
    private static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withInitScript("schema.sql")
            .withReuse(true);

    @BeforeAll
    public static void setup(){
        mySQLContainer.start();;
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    public void getItemsCount(){
        /*mySQLContainer.withClasspathResourceMapping("application.properties", "/tmp/application.properties", BindMode.READ_ONLY);
        mySQLContainer.withFileSystemBind("C:\\venkat\\app.xml", "/tmp/test.xml", BindMode.READ_ONLY);*/
        //mySQLContainer.execInContainer("ls", "-la");
        //String stdOut = mySQLContainer.getLogs(OutputFrame.OutputType.STDOUT);

        Integer onMyMachinePortNum = mySQLContainer.getMappedPort(3306);
        System.out.println("Port number :" + onMyMachinePortNum.intValue());
        List<Item> items = itemService.findAll();
        System.out.println("Size of items : " + items.size());
        assertThat(items).hasSize(2);
    }

}