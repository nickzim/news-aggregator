package nickzim.view;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import nickzim.model.NewsFeed;
import nickzim.services.contracts.FeedsService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Route("feeds")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FeedsPage extends VerticalLayout {

    private final FeedsService service;

    @PostConstruct
    public void init(){

        Grid<NewsFeed> russianFeeds = new Grid<>(NewsFeed.class);
        russianFeeds.setItems(service.getAllFeedsByLanguage("Russian"));
        russianFeeds.getColumnByKey("name").setHeader("News source");
        russianFeeds.removeColumnByKey("url");
        russianFeeds.removeColumnByKey("id");
        russianFeeds.removeColumnByKey("language");
        russianFeeds.setHeightByRows(true);

        Grid<NewsFeed> englishFeeds = new Grid<>(NewsFeed.class);
        englishFeeds.setItems(service.getAllFeedsByLanguage("English"));
        englishFeeds.getColumnByKey("name").setHeader("News source");
        englishFeeds.removeColumnByKey("url");
        englishFeeds.removeColumnByKey("id");
        englishFeeds.removeColumnByKey("language");
        englishFeeds.setHeightByRows(true);

        russianFeeds.addItemClickListener(e -> UI.getCurrent().
                navigate(CategoriesPage.class, e.getItem().getUrl().replaceAll("/","_")));

        englishFeeds.addItemClickListener(e -> UI.getCurrent().
                navigate(CategoriesPage.class, e.getItem().getUrl().replaceAll("/","_")));

        add(new Label("Russian news agencies"));
        add(russianFeeds);
        add(new Label("World news agencies"));
        add(englishFeeds);

    }

}
