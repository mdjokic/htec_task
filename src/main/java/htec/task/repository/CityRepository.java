package htec.task.repository;

import htec.task.model.City;
import htec.task.repository.projections.CityWithCommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "WITH LATEST AS(SELECT *, ROW_NUMBER() over(PARTITION BY city_id ORDER BY modified_at DESC) as RowNo from comments) " +
                   "" +
                    "SELECT ct.id, ct.country, ct.description, ct.name," +
                        "c.id as commentId, c.content as commentContent, c.modified_at as modifiedAt " +
                    "FROM cities ct LEFT JOIN LATEST c ON ct.id = c.city_id " +
                    "WHERE (rowno <= :numberOfComments OR rowno IS NULL) AND LOWER(ct.name) LIKE LOWER(:name)", nativeQuery = true)
    List<CityWithCommentProjection> findCitiesWithLatestComments(@Param("numberOfComments") int numberOfComments,
                                                                 @Param("name") String name);

    List<City> findCitiesByNameLikeIgnoreCase(String name);

    City findCityByNameAndCountry(String name, String Country);
}