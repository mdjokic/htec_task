package htec.task.mapper.city;

import htec.task.mapper.comment.CommentProjectionMapper;
import htec.task.model.City;
import htec.task.model.Comment;
import htec.task.repository.projections.CityWithCommentProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CityProjectionMapper {

    @Autowired
    private CommentProjectionMapper commentMapper;

    public List<City> toEntity(List<CityWithCommentProjection> projections){
        Long cityId;
        City city;
        Comment comment;
        HashMap<Long, City> cities = new HashMap<>();
        for (CityWithCommentProjection projection: projections) {
            if (projection.getCommentId() != null) {
                city = toEntity(projection);
                cityId = city.getId();
                comment = commentMapper.toEntity(projection);
                if (cities.containsKey(cityId)) {
                    cities.get(cityId).getComments().add(comment);
                } else {
                    city.getComments().add(comment);
                    cities.put(cityId, city);
                }
            }else{
                city = toEntity(projection);
                cityId = city.getId();
                if (!cities.containsKey(cityId)) {
                    cities.put(cityId, city);
                }
            }
        }
        return new ArrayList<>(cities.values());
    }

    public City toEntity(CityWithCommentProjection projection){
        City city = new City();
        city.setId(projection.getId());
        city.setName(projection.getName());
        city.setCountry(projection.getCountry());
        city.setDescription(projection.getDescription());
        return city;
    }
}
