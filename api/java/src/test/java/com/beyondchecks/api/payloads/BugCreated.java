package com.beyondchecks.api.payloads;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by dickert on 9-12-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BugCreated {
    @JsonProperty
    String id;

    public BugCreated()
    {

    }

    public String getId()
    {
        return id;
    }
}
