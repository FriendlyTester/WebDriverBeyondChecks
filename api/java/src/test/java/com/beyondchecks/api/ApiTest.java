package com.beyondchecks.api;

import com.beyondchecks.api.api.Bug;
import com.beyondchecks.api.payloads.BugPayload;
import com.beyondchecks.api.payloads.BugUpdatePayload;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ApiTest {

    @Test
    public void checkForBug(){
        ResponseEntity<String> result = Bug.getBug(1);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void checkForBugCreation(){
        BugPayload bugToCreate = new BugPayload("TestProduct", "TestComponent", "testing", "unspecified", "Windows", "PC");

        ResponseEntity<String> result = Bug.postBug(bugToCreate);

        assertEquals(result.getStatusCodeValue(), 200);
    }
    @Test
    public void checkupdateBugStatus(){
        BugUpdatePayload bugUpdate = new BugUpdatePayload(273,"CONFIRMED");

        ResponseEntity<String> result = Bug.changeBugStatus(bugUpdate);

        assertEquals(result.getStatusCodeValue(), 200);
    }


}
