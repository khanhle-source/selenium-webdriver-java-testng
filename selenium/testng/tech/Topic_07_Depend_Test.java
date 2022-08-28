package testng.tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_Depend_Test {
    @Test()
    public void Product_01_Create () {
        Assert.assertTrue(false);
    }

    @Test (dependsOnMethods ="Product_01_Create")
    public void Product_01_Update () {
    }

    @Test (dependsOnMethods={"Product_01_Create", "Product_01_Update"})
    public void Product_01_Delete () {

    }

}

