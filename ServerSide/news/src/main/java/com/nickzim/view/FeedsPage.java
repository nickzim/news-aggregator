package com.nickzim.view;


import com.nickzim.model.NewsFeed;
import com.nickzim.service.contract.FeedsService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Route("feeds")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FeedsPage extends VerticalLayout {

    private final FeedsService service;

    @PostConstruct
    public void init(){
        
        add(new Label("Russian news agencies"));
        add(createGrid("Russian"));
        add(new Label("World news agencies"));
        add(createGrid("English"));

    }

    private Grid<NewsFeed> createGrid(String language){

        Grid<NewsFeed> grid = new Grid<>(NewsFeed.class);
        grid.setItems(service.getAllFeedsByLanguage(language));
        grid.getColumnByKey("name").setHeader("News source");
        grid.removeColumnByKey("url");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("language");
        grid.setHeightByRows(true);

        grid.addItemClickListener(e -> UI.getCurrent().
                navigate(CategoriesPage.class, e.getItem().getUrl().replaceAll("/","_")));

        return grid;
    }

}
