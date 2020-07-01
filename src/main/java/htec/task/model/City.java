package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table( name = "cities")
public class City {

    @Id
    @GeneratedValue(generator = "city_seq_gen")
    @SequenceGenerator(name = "city_seq_gen", sequenceName = "city_seq_gen", initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public City() {
        comments = new ArrayList<>();
    }

    public City(String name, String country, String description){
        this.id = null;
        this.name = name;
        this.country = country;
        this.description = description;
    }

}
