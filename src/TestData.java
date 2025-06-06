import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestData {

	WebDriver driver = new ChromeDriver();

	JavascriptExecutor JS = (JavascriptExecutor) driver;
	Random rand = new Random();
	Actions move;

	String URL = "https://www.booking.com/";
	String SubscribeURL = "https://www.booking.com/subscribe.html";
	String[] MyWebsites = {
			"https://www.booking.com/index.html?label=gen173bo-1DCAEoggI46AdIAVgDaHSIAQGYAQG4ARfIAQzYAQPoAQH4AQOIAgGYAgKoAgO4AvydgsIGwAIB0gIkZWFjOWE4YTgtMTU5Ni00ODg4LWFjZjItMzgyZWRmOThlOTkw2AIE4AIB&sid=da309ea8c28a1dcbc920ab4a62a21775&lang_changed=1&sb_price_type=total&lang=en-us&soz=1",
			"https://www.booking.com/index.ar.html?label=gen173bo-1DCAEoggI46AdIAVgDaHSIAQGYAQG4ARfIAQzYAQPoAQH4AQOIAgGYAgKoAgO4AvydgsIGwAIB0gIkZWFjOWE4YTgtMTU5Ni00ODg4LWFjZjItMzgyZWRmOThlOTkw2AIE4AIB&sid=da309ea8c28a1dcbc920ab4a62a21775&lang_changed=1&sb_price_type=total&lang=ar&soz=1" };
	int RandomWebsiteIndex = rand.nextInt(MyWebsites.length);
	String ExpectedLanguage = "en-us";
	String ExpectedCurrency = "JOD";
	String ExpectedSubscribeValid = "Thanks!";

	String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	String TommorowDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	String[] EnglishCities = { "jeddah", "riyadh", "dubai", "Amman" };
	String[] ArabicCities = { "دبي", "جدة", "عمان" };

	int RandomEnglishCityIndex = rand.nextInt(EnglishCities.length);
	int RandomArabicCityIndex = rand.nextInt(ArabicCities.length);
	String RandomEnglishCity = EnglishCities[RandomEnglishCityIndex];
	String RandomArabicCity = ArabicCities[RandomArabicCityIndex];

	String[] firstNames = { "Ali", "Omar", "Salma", "Laila", "Khalid", "Nour", "Mona", "Yousef", "Tariq", "Huda" };
	String[] lastNames = { "Ahmad", "Hussein", "Saleh", "Jaber", "Nasser", "Zein", "Farah", "Khatib", "Darwish",
			"Najjar" };
	String[] phoneNumbers = { "791234567", "781112233", "771234567", "782345678", "793456789", "771000111", "789876543",
			"777654321", "790123456", "781998877" };
	String CardNumber = "4242424242424242";
	String ExpDate = "12/28";
	String cnn = "123";
	int FirstNameNumber = rand.nextInt(firstNames.length);
	int LastNameNumber = rand.nextInt(lastNames.length);
	int Numbers = rand.nextInt(phoneNumbers.length);

	public void EnterTheWebsite() {

		// Initialize Actions here after driver is ready
		move = new Actions(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	// Closing The Pop Up
	public void ClosingThePopUp() {
		if (driver.findElements(By.xpath("//button[@aria-label='Dismiss sign-in info.']")).size() > 0
				&& driver.findElement(By.xpath("//button[@aria-label='Dismiss sign-in info.']")).isDisplayed()) {
			WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-label='Dismiss sign-in info.']"));
			CloseButton.click();
		}
		// IF The Language Is Arabic
		if (driver.findElements(By.xpath("//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']")).size() > 0
				&& driver.findElement(By.xpath("//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']"))
						.isDisplayed()) {
			WebElement CloseButton = driver
					.findElement(By.xpath("//button[@aria-label='إخفاء المعلومات حول تسجيل الدخول.']"));
			CloseButton.click();
		}
	}

	public void CheckTheLanguageAndSendRandomCityName() throws InterruptedException {

		// Check The Language
		if (driver.getCurrentUrl().equals(MyWebsites[0])) {
			String ActualLaguageForNow = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			String ExpectedLanguageForNow = "en-us";
			Assert.assertEquals(ActualLaguageForNow, ExpectedLanguageForNow);

			// Random City(English Names) And Choice The First Result
			// Choosing Random Destination

			WebElement DestinationField = driver.findElement(By.className("b915b8dc0b"));
			DestinationField.sendKeys(RandomEnglishCity);
			Thread.sleep(1000);
			WebElement TheListOfDestination = driver.findElement(By.className("e03644d405"));
			List<WebElement> Destinations = TheListOfDestination.findElements(By.tagName("li"));

			// Choosing The First Option In the List
			Destinations.get(0).click();

		} else {

			// Check The Language
			String ActualLaguageForNow = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			String ExpectedLanguageForNow = "ar";
			Assert.assertEquals(ActualLaguageForNow, ExpectedLanguageForNow);

			// Random City(Arabic Names) And Choice The First Result
			// Choosing Random Destination

			WebElement DestinationField = driver.findElement(By.className("b915b8dc0b"));
			DestinationField.sendKeys(RandomArabicCity);
			Thread.sleep(1000);
			WebElement TheListOfDestination = driver.findElement(By.className("e03644d405"));
			List<WebElement> Destinations = TheListOfDestination.findElements(By.tagName("li"));

			// Choosing The First Option In the List
			Destinations.get(0).click();
		}

	}

	// Take ScreenShot
	public void ScreenShot() throws IOException, InterruptedException {
		Thread.sleep(2000);

		String Mydate = new Date().toString().replace(":", "-");

		System.out.println(Mydate);

		Thread.sleep(1000);

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File("ScreenShots/" + Mydate + ".jpg");
		FileUtils.copyFile(SrcFile, DestFile);

		// Log the saved path
		System.out.println("Screenshot saved at: " + DestFile.getAbsolutePath());

	}
}
