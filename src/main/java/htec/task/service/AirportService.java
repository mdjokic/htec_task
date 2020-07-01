package htec.task.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface AirportService {

    void createAirports(MultipartFile file);
}
