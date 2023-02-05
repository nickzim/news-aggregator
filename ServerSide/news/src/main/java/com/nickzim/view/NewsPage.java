package com.nickzim.view;

import com.nickzim.service.contract.NewsService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import com.nickzim.model.News;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Route("news")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class NewsPage extends VerticalLayout implements HasUrlParameter<String> {

    private final NewsService service;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        String feed = parameter.split("\\+")[0].replaceAll("_","/");
        String category =  parameter.split("\\+")[1];

        Grid<News> grid = new Grid<>(News.class);
        List<News> newsList;

        if ("all".equals(category)){
            newsList = service.getAllFromFeed(feed);
        } else {
            newsList = service.getAllFromCategory(feed,category);
        }

        if (newsList.isEmpty()){
            add(new Label("News not available in this source"));
            return;
        }

        grid.setItems(newsList);
        grid.setHeightByRows(true);

        if(newsList.get(0).getDescription() == null) {
            grid.setColumns("title", "pubDate");
        } else {
            grid.setColumns("title", "description", "pubDate");
            grid.getColumnByKey("description").setHeader("Description");

        }

        grid.getColumnByKey("title").setHeader("Title");
        grid.getColumnByKey("pubDate").setHeader("Publication date");

        grid.setSortableColumns();

        grid.addItemClickListener(e -> {
            UI.getCurrent().getPage().setLocation(e.getItem().getLink());
        });

        add(grid);
    }


}