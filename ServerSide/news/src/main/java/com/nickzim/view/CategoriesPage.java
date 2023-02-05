package com.nickzim.view;

import com.nickzim.model.dto.CategoryDto;
import com.nickzim.service.contract.CategoryService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Route("categories")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CategoriesPage extends VerticalLayout implements HasUrlParameter<String> {

    private final CategoryService service;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        List<CategoryDto> categoryList = service.getAllDTOsForFeedUrl(parameter.replaceAll("_","/"));

        if (!categoryList.isEmpty()){
            Grid<CategoryDto> grid = new Grid<>(CategoryDto.class);
            grid.setItems(categoryList);
            grid.setHeightByRows(true);
            grid.setWidth("1500px");

            grid.setColumns("name", "count");
            grid.getColumnByKey("name").setHeader("Category").setWidth("1200px");
            grid.getColumnByKey("count").setHeader("Number of news").setWidth("300px");

            grid.getColumnByKey("count").setSortable(false);
            grid.getColumnByKey("name").setSortable(false);

            grid.addItemClickListener(e -> {
                UI.getCurrent().navigate(NewsPage.class, parameter + "+" + e.getItem().getName());
            });

            add(grid);
        }

        Button allCategories = new Button("All categories");
        allCategories.addClickListener(e -> {
            UI.getCurrent().navigate(NewsPage.class, parameter + "+" + "all");
        });

        add(allCategories);

    }


}