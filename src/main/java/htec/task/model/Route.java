package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table( name = "routes")
public class Route {

    @Id
    @GeneratedValue(generator = "route_seq_gen")
    @SequenceGenerator(name = "route_seq_gen", sequenceName = "route_seq_gen", initialValue = 1)
    private Long id;

    @Column(name = "airline_id", nullable = false)
    private Long airlineId;

    @Column(name = "airline")
    private String airline;

    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    @Column(name = "codeshare")
    private char codeshare;

    @Column(name = "stops", nullable = false)
    private int stops;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Route(){ }

}
