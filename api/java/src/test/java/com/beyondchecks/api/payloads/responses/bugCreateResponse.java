package com.beyondchecks.api.payloads.responses;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Robert on 09/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class bugCreateResponse {
    @JsonProperty
    private int id;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}