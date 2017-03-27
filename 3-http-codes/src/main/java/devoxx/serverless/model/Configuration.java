package devoxx.serverless.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author fred
 */
@Data
public class Configuration {

    private String templateUrl;
    private List<Integer> codes;

}
