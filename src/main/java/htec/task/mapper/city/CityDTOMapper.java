package htec.task.mapper.city;

import htec.task.mapper.comment.CommentDTOMapper;
import htec.task.model.City;
import htec.task.web.dto.city.CityGetDTO;
import htec.task.web.dto.city.CityPostDTO;
import htec.task.web.dto.comment.CommentGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDTOMapper {

    @Autowired
    private CommentDTOMapper commentMapper;

    public City toEntity(CityPostDTO cityPostDTO){
        return new City(cityPostDTO.getName(), cityPostDTO.getCountry(), cityPostDTO.getDescription());
    }

    public CityGetDTO toDTO(City city){
        CityGetDTO cityDTO = new CityGetDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setDescription(city.getDescription());
        List<CommentGetDTO> commentsDTO = city.getComments()
                                            .stream().map(commentMapper::toDTO).collect(Collectors.toList());
        cityDTO.setComments(commentsDTO);
        return cityDTO;
    }

}
