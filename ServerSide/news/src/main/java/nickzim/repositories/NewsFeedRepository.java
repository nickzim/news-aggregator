package nickzim.repositories;

import nickzim.model.dto.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {

    @Override
    List<NewsFeed> findAll();
}
