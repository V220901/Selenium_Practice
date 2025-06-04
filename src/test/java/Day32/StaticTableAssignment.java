package Day32;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/* Test Steps:
 * 1.Open Link in Browser
 * 2.Select values from dropdown & click on Find Flights button
 * 3.Finding the smallest value of price 
 * 4.Clicking on Choose Flight option for flight having lowest price
 * 5.Filling details to confirm booking
 * 6.Confirming Message of Thank You
 */

public class StaticTableAssignment {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://blazedemo.com/");
		driver.manage().window().maximize();
		
		// Selecting data from dropdown
		WebElement dp_city=driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select depart_city= new Select(dp_city);
		depart_city.selectByValue("Mexico City");
		
		WebElement ds_city = driver.findElement(By.xpath("//select[@name='toPort']"));
	    Select destined_city = new Select(ds_city);
	    destined_city.selectByIndex(4);
	    
	    driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
	    
	    // Moving to new page after clicking on find Flights
	    
	    int rows = driver.findElements(By.xpath("//table[@class='table']//tbody//tr")).size();

        List<String> price_list= new ArrayList();
	    for (int r = 1; r <= rows; r++) {
	        WebElement priceCell = driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]//td[6]"));
	        String price_amount = priceCell.getText().replace("$","").trim();
	        price_list.add(price_amount);
	    }

	 price_list.sort(null);
	 System.out.println(price_list);
	 
	 String small_price = "$"+price_list.get(0);
	 System.out.println(small_price);
	 
	 // Clicking on Choose Flight for the flight having lowest price
	 for (int r = 1; r <= rows; r++) {
		    WebElement priceCell = driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]//td[6]"));
		    String price = priceCell.getText();
		    if (price.equals(small_price)) {
		        driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]//td[1]")).click();
		        Thread.sleep(5000);
		        break;
		    }
		}
	 
	 // Giving Details to next page for booking
	 driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys("Vaishali");
	 driver.findElement(By.xpath("//input[@id='address']")).sendKeys("Dharuhera");
	 driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Rewari");
	 driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Haryana");
	 driver.findElement(By.xpath("//input[@id='zipCode']")).sendKeys("123106");
	 
	 WebElement select_card = driver.findElement(By.xpath("//select[@id='cardType']"));
	 Select card_type = new Select(select_card);
	 card_type.selectByIndex(0);
	 
	 driver.findElement(By.xpath("//input[@id='creditCardNumber']")).sendKeys("123456879557");
	 driver.findElement(By.xpath("//input[@id='creditCardMonth']")).sendKeys("10");
	 driver.findElement(By.xpath("//input[@id='creditCardYear']")).sendKeys("2011");
	 driver.findElement(By.xpath("//input[@id='nameOnCard']")).sendKeys("Vaishali Yadav");
	 
	 driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
	 
	 
	 // Validating thank you message at last
     String expectedText = "Thank you for your purchase today!";
     WebElement confirmation_msg = driver.findElement(By.xpath("//h1[normalize-space()='Thank you for your purchase today!']"));
     //Assert.assertEquals(expectedText,confirmation_msg.getText());
     if(expectedText.equals(confirmation_msg.getText())) {
    	 System.out.println("Purchased successfully");
     }
	 
	}
	 }

	 
