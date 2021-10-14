package cz.uhk.ppro.inzeraty.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cz.uhk.ppro.inzeraty")
public class InzeratyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InzeratyApplication.class, args);
    }

}
