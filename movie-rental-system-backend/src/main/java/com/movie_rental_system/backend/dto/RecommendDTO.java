package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.MovieReview;
import com.movie_rental_system.backend.entity.Recommend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDTO {
    private Integer recommend_id;
    private Integer movie_id;
    private String recommender_user_name;
    private String recommended_user_name;
    private String message;

    public RecommendDTO(Recommend recommend) {
        this.recommend_id = recommend.getRecommend_id();
        this.movie_id = recommend.getMovie_id().getMovie_id();
        this.recommender_user_name = recommend.getRecommender_user_name().getUser_name();
        this.recommended_user_name = recommend.getRecommended_user_name().getUser_name();
        this.message = recommend.getMessage();
    }

    public static List<RecommendDTO> toRecommendDTOList(List<Recommend> recommends) {
        return recommends.stream().map(RecommendDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
