package htec.task.mapper;

import htec.task.model.City;
import htec.task.repository.dao.CityWithCommentProjection;
import htec.task.web.dto.city.CityGetDTO;
import htec.task.web.dto.city.CityPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    @Autowired
    CommentMapper commentMapper;

    public City toEntity(CityPostDTO cityPostDTO){
        return new City(cityPostDTO.getName(), cityPostDTO.getCountry(), cityPostDTO.getDescription());
    }

    public CityGetDTO toDTO(CityWithCommentProjection city){
        CityGetDTO cityGetDTO = new CityGetDTO();
        cityGetDTO.setId(city.getId());
        cityGetDTO.setName(city.getName());
        cityGetDTO.setCountry(city.getCountry());
        cityGetDTO.setDescription(city.getDescription());
        //List<CommentGetDTO> comments = city.getComments().stream().map(commentMapper::toDTO).collect(Collectors.toList());
        //cityGetDTO.setComments(comments);
        return cityGetDTO;
    }
}
