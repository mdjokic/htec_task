package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Getter
@Setter
@Entity
@Table( name = "airports")
public class Airport {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "sourceAirport", fetch = FetchType.LAZY)
    private List<Route> routes;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "iata_code", unique = true)
    private String IATACode;

    @Column(name = "icao_code", unique = true)
    private String ICAOCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Double altitude;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
