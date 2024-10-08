package vn.edu.hanu.fit.ss1.group1.session7.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyController {

    // Use a static variable to retain data across requests
    private static final Map<String, Person> personDatabase = new HashMap<>();

    // Sample data for demonstration
    static {
        personDatabase.put("123", new Person("John", "Doe"));
        personDatabase.put("456", new Person("Jane", "Smith"));
        personDatabase.put("789", new Person("Alice", "Johnson"));
    }
    // GET request that returns a JSON object
    @GetMapping("/json")
    public Map<String, String> getJsonObject() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, this is a sample JSON response!");
        response.put("status", "success");
        return response;
    }
    // GET request that retrieves a greeting message for a specific person by ID
    @GetMapping("/greet/{id}")
    public String greetPersonById(@PathVariable String id) {
        Person person = personDatabase.get(id);
        if (person != null) {
            String fullName = person.getFirstName() + " " + person.getLastName();
            return "Hello, " + fullName + ", Your id is " + id;
        } else {
            return "Person not found for ID: " + id;
        }
    }

    // POST request that accepts a request body and multiple URL parameters
    @PostMapping("/greet")
    public Map<String, String> greetPersonsPost(@RequestParam List<String> ids, @RequestBody List<Person> persons) {
        Map<String, String> responses = new HashMap<>();

        for (int i = 0; i < persons.size(); i++) {
            String fullName = persons.get(i).getFirstName() + " " + persons.get(i).getLastName();
            personDatabase.put(ids.get(i), persons.get(i));  // Store the person in the map
            responses.put(ids.get(i), "Hello, " + fullName + ", Your id is " + ids.get(i));
        }

        return responses;
    }
}

