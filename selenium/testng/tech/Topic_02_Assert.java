package testng.tech;

import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_02_Assert {
    @Test
    public void TC_01 () {
        String addressCity = "Ho Chi Minh";

        //Verify expected condition is true
        Assert.assertTrue(addressCity.equals("Ho Chi Minh"));
        Assert.assertTrue(addressCity.contains("Chi Minh"));
        Assert.assertTrue(addressCity.contains("Chi Minh"), "Address not contain Chi Minh");

        //Verify expected condition is false
        Assert.assertFalse(addressCity.contains("Ha Noi"));
        Assert.assertFalse(addressCity.contains("Da Nang"));

        //Verify expected and actual is equal
        Assert.assertEquals(addressCity, "Ho Chi Minh");

        // Verify expected condition is null/ not null
        Object fullname = null;
        Assert.assertNull(fullname);

        Topic_02_Assert topic_02 = null;
        Assert.assertNull(topic_02);

        fullname = "Le Nhu Khanh";
        Assert.assertNotNull(fullname);



    }

}
