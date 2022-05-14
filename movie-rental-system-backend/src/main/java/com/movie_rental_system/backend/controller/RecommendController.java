package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.RecommendDTO;
import com.movie_rental_system.backend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommends")
public class RecommendController {
    private final RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    // get all recommends
    @GetMapping
    public ResponseEntity<List<RecommendDTO>> getAllRecommends() {
        return ResponseEntity.ok(RecommendDTO.toRecommendDTOList(recommendService.getAllRecommendations()));
    }

    // get recommend by id
    @GetMapping("/{id}")
    public ResponseEntity<RecommendDTO> getRecommendById(@PathVariable Integer id) {
        return ResponseEntity.ok(new RecommendDTO(recommendService.getRecommendationById(id)));
    }

    // create recommend
    @PostMapping
    public ResponseEntity<RecommendDTO> createRecommend(@RequestBody RecommendDTO recommendDTO) {
        return ResponseEntity.ok(new RecommendDTO(recommendService.createRecommendation(recommendDTO)));
    }

    // update recommend
    @PutMapping("/{id}")
    public ResponseEntity<RecommendDTO> updateRecommend(@PathVariable Integer id, @RequestBody RecommendDTO recommendDTO) {
        return ResponseEntity.ok(new RecommendDTO(recommendService.updateRecommendation(id, recommendDTO)));
    }

    // delete recommend by id
    @DeleteMapping("/{id}")
    public ResponseEntity<RecommendDTO> deleteRecommendById(@PathVariable Integer id) {
        return ResponseEntity.ok(new RecommendDTO(recommendService.deleteRecommendation(id)));
    }


}
