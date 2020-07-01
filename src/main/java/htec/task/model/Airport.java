package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Getter
@Setter
@Entity
@Table( name = "airports")
public class Airport {

    @Id
    @GeneratedValue(generator = "airport_seq_gen")
    @SequenceGenerator(name = "airport_seq_gen", sequenceName = "airport_seq_gen", initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "iata_code", unique = true)
    private String IATACode;

    @Column(name = "icao_code", unique = true)
    private String ICAOCode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "altitude")
    private double altitude;

    @Column(name = "timezone")
    private ZoneOffset timezone;

    @Column(name = "dst")
    private char DST;

    @Column(name = "tz")
    private TimeZone tz;

    @Column(name = "type")
    private String type;

    @Column(name = "source")
    private String source;


}
