package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting loginTest");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		sleep(2000);

		driver.manage().window().maximize();
		sleep(2000);

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(2000);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(5000);

		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

//		Verifications
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as the expected");

		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage,"Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message\n");

		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
