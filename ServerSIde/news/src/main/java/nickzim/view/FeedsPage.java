package nickzim.view;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import nickzim.model.dto.RssFeedDto;
import nickzim.services.contracts.FeedsService;
import nickzim.services.impls.FeedsServiceImpl;

@Route("feeds")
public class FeedsPage extends VerticalLayout {

    private FeedsService service = new FeedsServiceImpl();


    public FeedsPage() {

        Grid<RssFeedDto> grid = new Grid<>(RssFeedDto.class);
        grid.setItems(service.getAllFeeds());
        grid.getColumnByKey("name").setHeader("Новостное агентство");
        grid.removeColumnByKey("url");

        grid.addItemClickListener(e -> {
            UI.getCurrent().navigate(CategoriesPage.class, e.getItem().getUrl().replaceAll("/","_"));
        });

        add(grid);
    }

}
