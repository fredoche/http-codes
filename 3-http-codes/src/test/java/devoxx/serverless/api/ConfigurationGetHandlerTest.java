package devoxx.serverless.api;

import devoxx.serverless.api.ConfigurationGetHandler;
import org.junit.Test;

/**
 *
 * @author frederic_boulet
 */
public class ConfigurationGetHandlerTest {

    @Test
    public void iterate_on_interface() throws Exception {
        ConfigurationGetHandler handler = new ConfigurationGetHandler();
        handler.doHandle(null);
    }
}
