package nickzim.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import nickzim.model.News;
import nickzim.services.contracts.NewsService;
import nickzim.services.impls.NewsServiceImpl;

import java.util.List;

@Route("news")
public class NewsPage extends VerticalLayout implements HasUrlParameter<String> {

    private NewsService service = new NewsServiceImpl();

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
            add(new Label("Новости в данном источнике недоступны"));
            return;
        }

        grid.setItems(newsList);
        grid.setHeightByRows(true);

        if(newsList.get(0).getDescription() == null) {
            grid.setColumns("title", "pubDate");
        } else {
            grid.setColumns("title", "description", "pubDate");
            grid.getColumnByKey("description").setHeader("Описание");

        }

        grid.getColumnByKey("title").setHeader("Название");
        grid.getColumnByKey("pubDate").setHeader("Дата публикации");

        grid.setSortableColumns();

        grid.addItemClickListener(e -> {
            UI.getCurrent().getPage().setLocation(e.getItem().getLink());
        });

        add(grid);
    }


}