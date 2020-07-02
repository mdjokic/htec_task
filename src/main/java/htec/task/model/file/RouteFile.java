package htec.task.model.file;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RouteFile {

    private String airline;

    private Long airlineId;

    private String sourceAirport;

    private Long sourceAirportId;

    private String destinationAirport;

    private Long destinationAirportId;

    private char codeshare;

    private int stops;

    private String equipment;

    private BigDecimal price;

    public RouteFile(){ }

    public RouteFile(String line){
        String[] tokens = line.split(",");
        this.airline = tokens[0].trim();
        setAirlineId(tokens[1].trim());
        this.setSourceAirport(tokens[2].trim());
        setSourceAirportId(tokens[3].trim());
        this.setDestinationAirport(tokens[4].trim());
        setDestinationAirportId(tokens[5].trim());
        this.setCodeshare(tokens[6].trim());
        this.stops = Integer.parseInt(tokens[7].trim());
        this.equipment = tokens[8].trim();
        this.price = new BigDecimal(tokens[9].trim());
    }

    public void setAirlineId(String airlineId){
        try {
            this.airlineId = Long.parseLong(airlineId);
        }catch (NumberFormatException e){
            this.airlineId = null;
        }
    }

    public void setSourceAirport(String sourceAirport){
        if(sourceAirport.length() != 3 && sourceAirport.length() != 4){
            this.sourceAirport = null;
        }else{
            this.sourceAirport = sourceAirport;
        }
    }

    public void setSourceAirportId(String sourceAirportId){
        try {
            this.sourceAirportId = Long.parseLong(sourceAirportId);
        }catch (NumberFormatException e){
            this.sourceAirportId = null;
        }
    }

    public void setDestinationAirport(String destinationAirport){
        if(destinationAirport.length() != 3 && destinationAirport.length() != 4){
            this.destinationAirport = null;
        }else{
            this.destinationAirport = destinationAirport;
        }
    }

    public void setDestinationAirportId(String destinationAirportId){
        try {
            this.destinationAirportId = Long.parseLong(destinationAirportId);
        }catch (NumberFormatException e){
            this.destinationAirportId = null;
        }
    }

    public void setCodeshare(String codeshare){
        if(codeshare.equals("Y")){
            this.codeshare = 'Y';
        }else{
            this.codeshare = ' ';
        }
    }


}
