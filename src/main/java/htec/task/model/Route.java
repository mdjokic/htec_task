package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "source_airport_id", nullable = false)
    private Long sourceAirportId;

    @Column(name = "source_airport")
    private String sourceAirport;

    @Column(name = "destination_airport_id", nullable = false)
    private Long destinationAirportId;

    @Column(name = "destination_airport")
    private String destinationAirport;

    @Column(name = "codeshare")
    private char codeshare;

    @Column(name = "stops", nullable = false)
    private int stops;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "price", nullable = false)
    private double price;

    public Route(){ }

}
