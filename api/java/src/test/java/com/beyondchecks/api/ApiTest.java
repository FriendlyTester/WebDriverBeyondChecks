package com.beyondchecks.api;

import com.beyondchecks.api.api.Bug;
import com.beyondchecks.api.payloads.BugCreated;
import com.beyondchecks.api.payloads.BugModel;
import com.beyondchecks.api.payloads.BugModelList;
import com.beyondchecks.api.payloads.BugPayload;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ApiTest {

    @Test
    public void checkForBug(){
        ResponseEntity<String> result = Bug.getBug(1);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void checkForBugCreation(){
        BugPayload bugToCreate = createSimpleBugPayload();

        ResponseEntity<String> result = Bug.postBug(bugToCreate);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void checkForBugValues()
    {
        final BugPayload bugToCreate = createSimpleBugPayload();
        ResponseEntity<BugCreated> result = Bug.postBugForCreated(bugToCreate);
        assertEquals(result.getStatusCodeValue(), 200);
        final BugCreated bugCreated = result.getBody();
        final int bugId = getIdOfCreatedBug(bugCreated);
        final BugModelList bugList = Bug.getBugForModelList(bugId).getBody();

        Assert.assertFalse(bugList.getBugs().isEmpty());
        final BugModel bugModel = bugList.getBugs().get(0);
        Assert.assertEquals(bugToCreate.getComponent(), bugModel.getComponent());
        Assert.assertEquals(bugToCreate.getProduct(), bugModel.getProduct());
        Assert.assertEquals(bugToCreate.getOp_sys(), bugModel.getOp_sys());
        Assert.assertEquals(bugToCreate.getSummary(), bugModel.getSummary());
        Assert.assertEquals(bugToCreate.getVersion(), bugModel.getVersion());
    }

    private BugPayload createSimpleBugPayload()
    {
        return new BugPayload("TestProduct", "TestComponent", "testing", "unspecified", "Windows", "PC");
    }

    private Integer getIdOfCreatedBug(BugCreated bugCreated)
    {
        Integer idOfCreatedBug = null;
        try
        {
            idOfCreatedBug = Integer.parseInt(bugCreated.getId());

        }
        catch (NumberFormatException e)
        {
            fail("Unable to parse returned ID of bug which you just created.");
        }
        return idOfCreatedBug;
    }
}
