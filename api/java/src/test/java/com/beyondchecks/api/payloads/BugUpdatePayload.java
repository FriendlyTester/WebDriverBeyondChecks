package com.beyondchecks.api.payloads;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BugUpdatePayload {

    @JsonProperty
    private Integer bugID;
    @JsonProperty
    private String status;

    public BugUpdatePayload(Integer bugID, String status){
        this.bugID = bugID;
        this.status = status;

    }

    public Integer getBugID() {
        return bugID;
    }

    public String getStatus() {
        return status;
    }
}
