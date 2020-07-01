package htec.task.repository.projections;
import java.time.Instant;

public interface CityWithCommentProjection {

    Long getId();

    String getCountry();

    String getDescription();

    String getName();

    Long getCommentId();

    String getCommentContent();

    Instant getModifiedAt();

}
