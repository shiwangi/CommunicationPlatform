package com.meesho.dataproviders;

import java.util.List;
import java.util.Map;

/**
 * A data provider helps fetch details about a given set of parameters, so that the response can be used in
 * the templates.
 */
public interface DataProvider {
    List<String> inputFields = null;
    Map<String, String> getDetails(final Object obj);
}
