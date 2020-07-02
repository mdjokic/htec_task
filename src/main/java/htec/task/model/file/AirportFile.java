package htec.task.model.file;

import lombok.Getter;
import lombok.Setter;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Getter
@Setter
public class AirportFile {

    private Long id;

    private String name;

    private String city;

    private String country;

    private String iATA;

    private String iCAO;

    private Double latitude;

    private Double longitude;

    private Double altitude;

    private ZoneOffset timezone;

    private char dST;

    private TimeZone tz;

    private String type;

    private String source;

    public AirportFile() { }

    public AirportFile(String line){
        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        List<String> tokensString = Arrays.stream(tokens).map(s -> s.replace("\"", " ").trim()).collect(Collectors.toList());
        this.id = Long.parseLong(tokensString.get(0));
        this.name = tokensString.get(1);
        this.city = tokensString.get(2);
        this.country = tokensString.get(3);
        setIATA(tokensString.get(4));
        setICAO(tokensString.get(5));
        setLatitude(tokensString.get(6));
        setLongitude(tokensString.get(7));
        setAltitude(tokensString.get(8));
        setTimezone(tokensString.get(9));
        setDST(tokensString.get(10));
        this.tz = TimeZone.getTimeZone(tokensString.get(11));
        this.type = tokensString.get(12);
        this.source = tokensString.get(13);
    }

    public void setICAO(String ICAO){
        if(ICAO.length() != 4){
            this.iCAO = null;
        }else{
            this.iCAO = ICAO;
        }
    }
    public void setIATA(String IATA){
        if(IATA.length() != 3){
            this.iATA = null;
        }else{
            this.iATA = IATA;
        }
    }

    public void setLatitude(String latitude){
        try {
            this.latitude = Double.parseDouble(latitude);
        }catch (NumberFormatException e){
            this.latitude = null;
        }
    }

    public void setLongitude(String longitude){
        try {
            this.longitude = Double.parseDouble(longitude);
        }catch (NumberFormatException e){
            this.longitude = null;
        }
    }

    public void setAltitude(String altitude){
        try {
            this.altitude = Double.parseDouble(altitude);
        }catch (NumberFormatException e){
            this.altitude = null;
        }
    }

    public void setDST(String dST){
        if(dST.length() == 1){
            this.dST = dST.charAt(0);
        }else{
            this.dST = 'U';
        }
    }

    public void setTimezone(String timezone){
        try {
            double hoursAndMinutes = Double.parseDouble(timezone);
            int hours = (int) hoursAndMinutes;
            int minutes = (int) (60 * (hoursAndMinutes - hours));
            this.timezone = ZoneOffset.ofHoursMinutes(hours, minutes);
        }catch (NumberFormatException e){
            this.timezone = null;
        }

    }


}
