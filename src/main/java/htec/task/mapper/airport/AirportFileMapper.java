package htec.task.mapper.airport;

import htec.task.model.Airport;
import htec.task.model.City;
import htec.task.model.file.AirportFile;
import org.springframework.stereotype.Component;

@Component
public class AirportFileMapper {

    public Airport toEntity(AirportFile airportFile, City city){
        Airport airport = new Airport();
        airport.setId(airportFile.getId());
        airport.setName(airportFile.getName());
        airport.setCity(city);
        airport.setCountry(airportFile.getCountry());
        airport.setIATACode(airportFile.getIATA());
        airport.setICAOCode(airportFile.getICAO());
        airport.setLatitude(airportFile.getLatitude());
        airport.setLongitude(airportFile.getLongitude());
        airport.setAltitude(airportFile.getAltitude());
        airport.setTimezone(airportFile.getTimezone());
        airport.setDST(airportFile.getDST());
        airport.setTz(airportFile.getTz());
        airport.setType(airportFile.getType());
        airport.setSource(airportFile.getSource());
        return airport;
    }
}
