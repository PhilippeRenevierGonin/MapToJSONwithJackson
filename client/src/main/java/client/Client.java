package client;

import data.SampleClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Client {


    public static void main(String args[]) {
        SpringApplication.run(Client.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            System.out.println("*********************** receiving ********************");
            // getting a SampleClass Object
            SampleClass sample = restTemplate.getForObject(
                    "http://localhost:8080/getsample", SampleClass.class);

            SampleClass.display(sample);


            // sending a SampleClass Object
            SampleClass sampleToSend = new SampleClass();

            SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");
            sampleToSend.setOtherValue("sending to the serveur");
            sampleToSend.getMap().put(new Point(25,12), df.parse("25-12-2019"));
            sampleToSend.getAnotherMap().put(new Point(14,7), df.parse("14-07-2019"));

            // displaying before sending
            System.out.println("*********************** sending ********************");
            SampleClass.display(sampleToSend);

            // sending (and getting a SampleClass object
            SampleClass modified = restTemplate.postForObject("http://localhost:8080/putsample", sampleToSend, SampleClass.class);

            // displaying the received one
            System.out.println("*********************** receiving ********************");
            SampleClass.display(modified);


        };

    }
}