package com.beyondchecks.api.payloads;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by dickert on 9-12-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BugModelList
{
    @JsonProperty
    private List<BugModel> bugs;

    public BugModelList()
    {

    }

    public List<BugModel> getBugs()
    {
        return bugs;
    }

    public void setBugs(List<BugModel> bugs)
    {
        this.bugs = bugs;
    }
}
