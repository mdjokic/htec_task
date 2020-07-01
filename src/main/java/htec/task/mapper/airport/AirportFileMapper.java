package htec.task.mapper.airport;

import htec.task.model.Airport;
import htec.task.model.City;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.TimeZone;

@Component
public class AirportFileMapper {

    public Airport toEntity(String[] tokens, City city){
        Airport airport = new Airport();

        Long airportId = Long.parseLong(tokens[0].trim());
        airport.setId(airportId);

        airport.setName(tokens[1].trim());
        airport.setCity(city);

        airport.setCountry(tokens[3].trim());
        airport.setIATACode(tokens[4].trim());
        airport.setICAOCode(tokens[5].trim());

        double latitude = Double.parseDouble(tokens[6].trim());
        airport.setLatitude(latitude);

        double longitude = Double.parseDouble(tokens[7].trim());
        airport.setLongitude(longitude);

        double altitude = Double.parseDouble(tokens[8].trim());
        airport.setAltitude(altitude);

        ZoneOffset timezone = ZoneOffset.ofHours(Integer.parseInt(tokens[9].trim()));
        airport.setTimezone(timezone);
        airport.setDST(tokens[10].trim().charAt(0));

        TimeZone tz = TimeZone.getTimeZone(tokens[11].trim());
        airport.setTz(tz);

        airport.setType(tokens[12].trim());
        airport.setSource(tokens[13].trim());

        return airport;
    }
}
