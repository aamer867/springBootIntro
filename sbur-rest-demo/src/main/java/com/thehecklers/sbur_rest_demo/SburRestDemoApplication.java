package com.thehecklers.sbur_rest_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

>>>>>>> chap2

import java.util.*;

@SpringBootApplication
public class SburRestDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SburRestDemoApplication.class, args);
    }
}

@RestController
class RestApiDemoController {
<<<<<<< HEAD
    private final List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
=======
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeRepository.saveAll(List.of(
>>>>>>> chap2
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }

    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees() {
<<<<<<< HEAD
        return coffees;
=======
        return coffeeRepository.findAll();
>>>>>>> chap2
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
<<<<<<< HEAD
        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
=======
        return coffeeRepository.findById(id);
>>>>>>> chap2
    }

    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/coffees/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
<<<<<<< HEAD
        int coffeeIndex = -1;
        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
                break;
            }
        }

        return (coffeeIndex == -1)
                ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(coffee, HttpStatus.OK);
=======
        return (!coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
>>>>>>> chap2
    }

    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }
}
<<<<<<< HEAD

class Coffee {
    private final String id;
=======
@Entity
class Coffee {
    @Id
    private String id;
>>>>>>> chap2
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Coffee() {
        this.id = UUID.randomUUID().toString();
    }

<<<<<<< HEAD
=======
    public void setId(String id) { this.id = id; }

>>>>>>> chap2
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
<<<<<<< HEAD
=======

interface CoffeeRepository extends CrudRepository<Coffee, String> {}




>>>>>>> chap2
