package htec.task.repository;

import htec.task.model.City;
import htec.task.repository.projections.CityWithCommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "WITH LATEST AS(SELECT *, ROW_NUMBER() over(PARTITION BY city_id ORDER BY modified_at DESC) as RowNo from comments) " +
            "            SELECT ct.id, ct.country, ct.description, ct.name," +
            "               c.id as commentId, c.content as commentContent, c.modified_at as modifiedAt " +
            "            FROM cities ct INNER JOIN LATEST c ON ct.id = c.city_id " +
            "            WHERE rowno <= :numberOfComments", nativeQuery = true)
    List<CityWithCommentProjection> getCitiesWithLatestComments(@Param("numberOfComments") int numberOfComments);

    City findCityByNameAndCountry(String name, String Country);
}
