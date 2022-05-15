package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.ActorDTO;
import com.movie_rental_system.backend.entity.Actor;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.exception.ActorNotFoundException;
import com.movie_rental_system.backend.exception.MovieNotFoundException;
import com.movie_rental_system.backend.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Integer id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null)
            throw new ActorNotFoundException("Actor with id " + id + " not found");
        return actor;
    }

    public Actor addActor(ActorDTO actorDTO) {
        Objects.requireNonNull(actorDTO, "actor cannot be null");
        Actor actor = new Actor(actorDTO.getActor_name(),actorDTO.getBirth_year());
        return actorRepository.save(actor);
    }

    public Actor updateActor(Integer id, ActorDTO actorDTO) {
        Objects.requireNonNull(actorDTO, "actor cannot be null");
        if (actorRepository.existsById(id)) {
            Actor actor = actorRepository.findById(id).get();
            actor.setActor_name(actorDTO.getActor_name());
            actor.setBirth_year(actorDTO.getBirth_year());
            return actorRepository.save(actor);
        }else {
            throw new ActorNotFoundException("Actor with id " + id + " is not found");
        }
    }

    public void deleteActor(Integer id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null)
            throw new ActorNotFoundException("Actor with id " + id + " not found");
        actorRepository.delete(actor);
    }

    public List<Movie> getMoviesOfActor(Integer id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null)
            throw new ActorNotFoundException("Actor with id " + id + " not found");
        return actor.getMovies();
    }
}
