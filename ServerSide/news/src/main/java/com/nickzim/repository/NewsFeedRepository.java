package com.nickzim.repository;

import com.nickzim.model.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {

    @Override
    List<NewsFeed> findAll();

    List<NewsFeed> findAllByLanguage(String language);
}
