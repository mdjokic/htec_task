package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public City() { }

    public City(String name, String country, String description){
        this.id = null;
        this.name = name;
        this.country = country;
        this.description = description;
    }

}
