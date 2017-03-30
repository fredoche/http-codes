package devoxx.serverless.api;

import devoxx.serverless.model.Configuration;
import devoxx.serverless.model.ConfigurationResponse;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;

public class ConfigurationGetHandler extends AbstractHandler {

    @Override
    public AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception {
        ConfigurationResponse response = new ConfigurationResponse();
        Configuration configuration = new Configuration();
        response.setConfiguration(configuration);

        Field[] interfaceFields = HttpStatus.class.getFields();

        List<Integer> codes = new ArrayList<>();
        for (Field f : interfaceFields) {
            codes.add(f.getInt(f));
        }

        /**
         * remove broken img for dogs
         */
        codes.remove((Integer) 101);
        codes.remove((Integer) 102);
        codes.remove((Integer) 205);
        codes.remove((Integer) 415);
        codes.remove((Integer) 419);
        codes.remove((Integer) 505);
        /**
         * remove broken img for cat
         */
        codes.remove((Integer) 203);
        codes.remove((Integer) 407);
        codes.remove((Integer) 501);

//        configuration.setTemplateUrl("https://httpstatusdogs.com/img/_code_.jpg");
        configuration.setTemplateUrl("https://http.cat/_code_");
        configuration.setCodes(codes);

        return createSuccessResponse(response);
    }
}
