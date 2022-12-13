package Sample_Extent_Report;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extent_Report_1 {
	WebDriver driver;
	
	@Test
	public void Report_Demo() throws IOException {
		
		
		//Create HTML report Templete
	ExtentHtmlReporter reporter= new ExtentHtmlReporter("./Report/ExecutionReport.html");
	
	//Attach the report to the HTML Templete
	ExtentReports reports=new ExtentReports();
	reports.attachReporter(reporter);
	
	//Create the test with test cases
	ExtentTest test=reports.createTest("Demo WebShop Regression");
	
	
	//Test steps
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://demowebshop.tricentis.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	test.log(Status.PASS, "Application launched sucessfully");
	
	//Capture_screen_shoot("Launch_DWS") 
	test.pass("application launched").addScreenCaptureFromPath(Capture_screen_shot("Launch DWS"));

	driver.findElement(By.id("small-searchterms")).sendKeys("computers");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	test.log(Status.PASS, "product search is sucessful");
	//test.log(Status.FAIL, "Product fail");
	test.log(Status.INFO, "search is completed");
	
	driver.close();
	reports.flush();
	}
	
	public String Capture_screen_shot(String stepname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		String destpath="./Snapshot/"+stepname+".png";
		FileHandler.copy(src, new File(destpath));
		return "."+destpath;
	
		
	}

}
