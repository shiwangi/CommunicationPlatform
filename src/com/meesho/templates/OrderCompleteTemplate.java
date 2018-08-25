package com.meesho.templates;

import com.meesho.dataproviders.DataProvider;
import com.meesho.dataproviders.OrderDetailsDataProvider;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderCompleteTemplate implements Template {

    @Resource
    OrderDetailsDataProvider orderDetailsDataProvider;

    @Resource
    TemplateEvaluator templateEvaluator;

    List<DataProvider> dataProviders = Arrays.asList(orderDetailsDataProvider);

    String path = "com/meesho/templates/hbs/order_complete.hbs";

    @Override
    public void send(Map<String, String> inputs, List<Channel> channelsList) {
        //Comnvert object to JSON
        List<String> fields = dataProviders.stream()
                                           .map(dataProvider -> dataProvider.inputFields)
                                           .flatMap(Collection::stream)
                                           .collect(Collectors.toList());
        List<String> fieldsNotAvailable = fields.stream()
                                                .filter(field -> !inputs.containsKey(field))
                                                .collect(Collectors.toList());
        if (!fieldsNotAvailable.isEmpty()) {
            //throw error

        }
        //if jsonObj contains all the fields, call data providers
        //relace hbs file with fields
        String content = templateEvaluator.render(path, inputs);
        channelsList.forEach(channel -> channel.send(content, customer));
    }
}
