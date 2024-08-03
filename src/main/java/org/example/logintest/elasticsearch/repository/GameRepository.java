package org.example.logintest.elasticsearch.repository;

import java.util.List;
import org.example.logintest.elasticsearch.entity.Game;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ElasticsearchRepository<Game, String> {
    List<Game> findByDescriptionContaining(String keyword);
}
