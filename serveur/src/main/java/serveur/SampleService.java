package serveur;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import data.SampleClass;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // to give a SampleClass Object
    @GetMapping("/getsample")
    public SampleClass exposesample() throws ParseException {
        SampleClass sample = new SampleClass();

        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");

        sample.setOtherValue("just an exmample");

        sample.getMap().put(new Point(24,12), new Date());
        sample.getMap().put(new Point(42,7), df.parse("28-03-2019"));
        sample.getAnotherMap().put(new Point(24,12), new Date());
        sample.getAnotherMap().put(new Point(42,7), df.parse("28-03-2019"));

        return sample;
    }


    // to get a SampleClass Object, adding a new datum, returning the modidied object
    @PostMapping("/putsample")
    public SampleClass readsample(@RequestBody SampleClass sample) throws ParseException {
        SampleClass.display(sample);
        sample.getMap().put(new Point(1,1), new Date());
        return sample;
    }
}
