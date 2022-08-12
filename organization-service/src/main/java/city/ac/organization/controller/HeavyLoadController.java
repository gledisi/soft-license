package city.ac.organization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heavy")
public class HeavyLoadController {

    @GetMapping
    public String start() {
        final int NUM_TESTS = 1000;
        for (int i = 0; i < NUM_TESTS; i++) {
            spin(500);
        }
        return "Finished";
    }

    private static void spin(int milliseconds) {
        long sleepTime = milliseconds * 1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {
        }
    }

}
