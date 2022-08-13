package testng.tech;

import org.testng.annotations.Test;

public class Topic_03_Enable_Priority_Desctiption {
    @Test (priority=1, enabled = true, description = "user view product")
    public void ViewProduct () {

    }

    @Test(priority=2, enabled = false, description = "user add product to cart")
    public void Add_To_Cart () {

    }

    @Test (priority=4)
    public void Add_Payment_Method () {

    }

    @Test (priority=3)
    public void Checkout () {

    }
}

