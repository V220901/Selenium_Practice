
package Day28;

/* Test Steps:
 * Opening link in browser
 * Typing Selenium in Search Box
 * Opening all links in new tab
 * Closing first tab of "Selenium" by own seperately
 * 
 */

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Switch_assignment {

	public static void main(String[] args) throws InterruptedException {
       WebDriver driver = new ChromeDriver();
       driver.get("https://testautomationpractice.blogspot.com/");
       driver.manage().window().maximize();
       
       // typing Selenium in Search Box
       driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']")).sendKeys("Selenium");
       Thread.sleep(3000);
       driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']/following:: input[1]")).click();
       
       Thread.sleep(3000);
       
       // Capturing all links
       List<WebElement> searched_links = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a")) ; 
       int total_results = searched_links.size();
       for(WebElement links:searched_links) {
    	       links.click();
    	}
       
       // Closing first browser window having Title Selenium
       Set<String> window_id = driver.getWindowHandles();
       for(String winid:window_id) {
    	    String title = driver.switchTo().window(winid).getTitle();
       if(title.equals("Selenium - Wikipedia")) {
    	   driver.close();
    	   break;
       }
    	   }
       
       String driver_t = driver.getTitle();
       
       System.out.println(driver_t);
       Thread.sleep(2000);
       driver.quit();
	}

}
