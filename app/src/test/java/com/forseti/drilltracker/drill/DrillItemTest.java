package com.forseti.drilltracker.drill;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DrillItemTest {
    @Test
    public void shouldHaveNameAndDescription() {
        DrillItem drillItem = new DrillItem("name", "description");

        System.out.println(drillItem);

        assertEquals(drillItem.name, "name");
        assertEquals(drillItem.description, "description");
    }
}