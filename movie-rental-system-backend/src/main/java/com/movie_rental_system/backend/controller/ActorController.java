package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.ActorDTO;
import com.movie_rental_system.backend.entity.Actor;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@CrossOrigin(origins = "http://localhost:3000")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAll(){
        return ResponseEntity.ok(actorService.getAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Actor> getActorById(@PathVariable Integer id){
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @PostMapping
    public ResponseEntity<?> addActor(@RequestBody ActorDTO actor){
        return ResponseEntity.ok(actorService.addActor(actor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Integer id, @RequestBody ActorDTO actor){
        return ResponseEntity.ok(actorService.updateActor(id, actor));
    }

    // delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Integer id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok("Actor deleted");
    }

    @GetMapping(value = "/{id}/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMoviesOfActor(@PathVariable Integer id){
        return ResponseEntity.ok(actorService.getMoviesOfActor(id));
    }
}
