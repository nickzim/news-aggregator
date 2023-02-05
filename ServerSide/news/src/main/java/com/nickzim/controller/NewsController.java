package com.nickzim.controller;

import lombok.RequiredArgsConstructor;
import com.nickzim.model.News;
import com.nickzim.service.contract.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<News>> getAllNews(){
        return ResponseEntity.ok(newsService.getAll());
    }
}
