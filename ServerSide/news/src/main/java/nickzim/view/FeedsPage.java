package nickzim.view;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nickzim.model.dto.NewsFeed;
import nickzim.services.contracts.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Route("feeds")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FeedsPage extends VerticalLayout {

    @Autowired
    private FeedsService service;

    public FeedsPage() {

    }

    @PostConstruct
    public void init(){
        Grid<NewsFeed> grid = new Grid<>(NewsFeed.class);
        grid.setItems(service.getAllFeeds());
        grid.getColumnByKey("name").setHeader("News source");
        grid.removeColumnByKey("url");
        grid.removeColumnByKey("id");
        grid.setHeightByRows(true);

        grid.addItemClickListener(e -> {
            UI.getCurrent().navigate(CategoriesPage.class, e.getItem().getUrl().replaceAll("/","_"));
        });

        add(grid);
    }

}
