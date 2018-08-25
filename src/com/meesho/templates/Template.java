package com.meesho.templates;

import com.meesho.dataproviders.DataProvider;

import java.util.List;
import java.util.Map;

public interface Template {

    List<DataProvider> dataProviders = null;
    String path = null;

    void send(Map<String,String> inputs, List<Channel> channelsList);
}
