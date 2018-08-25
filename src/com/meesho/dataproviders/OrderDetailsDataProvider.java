package com.meesho.dataproviders;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The OrderDetailsDataProvider provides details like, amount and time of the order placed.
 */
public class OrderDetailsDataProvider implements DataProvider {

    List<String> inputFields = Arrays.asList("orderId", "customerId");

    @Resource
    DownstreamServiceToFetchDetails downstreamServiceToFetchDetails;

    public Map<String, String> getDetails(final
                                          Object obj) throws MissingFieldsException {
        //convert to json and look for input fields.
        ObjectMapper mapper = new ObjectMapper();

        //Object to JSON in String
        JSON jsonInput = JSON.parse(mapper.writeValueAsString(obj));
        Optional<String> missingFields = inputFields.stream()
                                                    .filter(inputField -> !jsonInput.hasKey(inputField))
                                                    .findFirst();
        if (!missingFields.isPresent()) {
            throw new MissingFieldsException("");
        }
        //uses the facade to call the data provider.
        //make restAPI calls or table lookups to fetch the dets.
        return downstreamServiceToFetchDetails.call(jsonInput);
    }
}
