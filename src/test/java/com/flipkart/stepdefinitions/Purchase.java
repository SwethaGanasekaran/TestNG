package com.flipkart.stepdefinitions;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Purchase 
{
	static WebDriver driver;
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void launch()
	{
		WebDriverManager.chromedriver().setup();
		   driver=new ChromeDriver();
		   ((WebDriver) driver).get("https:\\flipkart.com");
		   ((WebDriver) driver).manage().window().maximize();
		   ((WebDriver) driver).manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   
		   
		}
	@AfterClass
    public static void quit()    
    {
		System.out.println("Quit");
		driver.quit();
		
    }
	static long startTime;
	
	@BeforeMethod
	public static void beforeMethod()
	{
		System.out.println("before method");
		startTime=System.currentTimeMillis();
		
	}
	@AfterMethod
	public static void afterMethod() 
	{
		System.out.println("after method");
		long endTime=System.currentTimeMillis();
		System.out.println("Time taken:"+(endTime - startTime));
	}
	@Test(priority=1)
	public void login()
	{
		System.out.println("Method 1");
		WebElement login=driver.findElement(By.xpath("/html/body/div[2]/div/div/button"));
		login.click();
		
	}
	@Test(priority=2)
	public void search()
	{
		System.out.println("Method 2");
		WebElement search=((WebDriver) driver).findElement(By.xpath("//input[@name='q']"));
	    search.sendKeys("Redmi mobiles",Keys.ENTER);
	    String mobile=search.getText();
	    System.out.println("Mobile Names:" +mobile);
	    
	    search.click();
	}
	@Test(priority=3)
	public void windows()
	{
		System.out.println("Method 3");
		String parent=((WebDriver) driver).getWindowHandle();
		
		Set<String> child=((WebDriver) driver).getWindowHandles();
		{
			for(String x1:child)
			{
				if(!x1.equals(parent))
				{
					((WebDriver) driver).switchTo().window(x1);
					System.out.println("Window Switched");
					
				}
			}}
		}
		
		
	    @Test(priority=4)	
		public void addToCart() 
	    {
		System.out.println("Add To Cart");
		System.out.println("DropDown & Screenshot");
		WebElement buy=((WebDriver) driver).findElement(By.xpath("//button[text()='BUY NOW]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("argument[0].scrollIntoView(true)", buy);
		String text=buy.getText();
		
		Assert.assertTrue(buy.isDisplayed());
		Assert.assertEquals("BUY NOW", text);
				
	}}
