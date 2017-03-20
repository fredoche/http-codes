package devoxx.serverless.model;

import java.util.Map;
import lombok.Data;

/**
 *
 * @author fred
 */
@Data
public class StatsResponse {

    private Map<String, Integer> statistics;
}
