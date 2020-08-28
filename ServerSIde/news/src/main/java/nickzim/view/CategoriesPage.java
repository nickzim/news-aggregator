package nickzim.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import nickzim.model.dto.CategoryDto;
import nickzim.services.contracts.CategoryService;
import nickzim.services.impls.CategoryServiceImpl;

@Route("categories")
public class CategoriesPage extends VerticalLayout implements HasUrlParameter<String> {

    private CategoryService service = new CategoryServiceImpl();


    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        Grid<CategoryDto> grid = new Grid<>(CategoryDto.class);
        grid.setItems(service.getAllDTOsForFeedUrl(parameter.replaceAll("_","/")));

        grid.setWidth("1500px");

        grid.setColumns("name", "count");
        grid.getColumnByKey("name").setHeader("Категория").setWidth("1200px");
        grid.getColumnByKey("count").setHeader("Количество новостей").setWidth("300px");

        grid.getColumnByKey("count").setSortable(false);
        grid.getColumnByKey("name").setSortable(false);



        grid.addItemClickListener(e -> {

        });


        add(grid);
    }

    public CategoriesPage() {

    }

}