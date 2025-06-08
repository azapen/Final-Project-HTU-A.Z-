
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends TestData {

	// 1. Search & Navigation
	@BeforeTest
	public void SetUp() {
		// 1.1 Navigate to Home page
		EnterTheWebsite();
		// Closing The Pop Up
		ClosingThePopUp();
	}

	@Test(priority = 1, enabled = true)
	public void CheckTheLanguage() throws InterruptedException {

		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);

	}

	@Test(priority = 2, enabled = true)
	public void CheckTheCurrency() throws InterruptedException {

		String ActualCurrency = driver.findElement(By.className("ca2ca5203b")).getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3, enabled = true)
	public void RandomlyChangeTheLanguage_And_Random_Ar_Or_En_Cities() throws InterruptedException {
		// Switching between Tabs
		Set<String> handels = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(handels);
		driver.switchTo().window(windowList.get(0));
		System.out.println(driver.getTitle());
		Thread.sleep(1000);

		// RandomlyChangeTheLanguage
		driver.get(MyWebsites[RandomWebsiteIndex]);
		// Closing The Pop Up
		ClosingThePopUp();

		// Checking The Language And Then Send Random City Name Based On The Language
		CheckTheLanguageAndSendRandomCityName();
		Thread.sleep(2000);

	}

	@Test(priority = 4, enabled = true)
	public void BookingSubscribeValid() throws InterruptedException {
		driver.get(SubscribeURL);
		WebElement Subscribe = driver.findElement(By.xpath("//input[@type='email']"));
		Subscribe.sendKeys("azazeben1@gmail.com");

		WebElement SubscribeButton = driver.findElement(By.xpath("//button[@type='submit']"));
		SubscribeButton.click();

		Thread.sleep(1000);
		List<WebElement> Confirmations = driver.findElements(By.xpath("//h1[@class='subscribe-header']"));
		System.out.println(Confirmations.get(1).getText());
		String ActualSubscribeValid = Confirmations.get(1).getText();
		Assert.assertEquals(ActualSubscribeValid, ExpectedSubscribeValid);

	}

	@Test(priority = 5, enabled = true)
	public void BookingSubscribeInvalid() throws InterruptedException {
		driver.get(SubscribeURL);
		WebElement Subscribe = driver.findElement(By.xpath("//input[@type='email']"));
		Subscribe.sendKeys("malal.fy@dod");

		WebElement SubscribeButton = driver.findElement(By.xpath("//button[@type='submit']"));
		SubscribeButton.click();

		Thread.sleep(1000);
		WebElement ErrorMassege = driver.findElement(By.xpath("//p[@role='alert']"));
		System.out.println(ErrorMassege.getText());
		String ActualSubscribeInvalid = ErrorMassege.getText();
		Assert.assertTrue(ActualSubscribeInvalid.contains("Please enter a valid email address.")
				|| ActualSubscribeInvalid.contains("الرجاء إدخال عنوان البريد الالكتروني"));

	}

	// 1.2 Search for Accommodations

	@Test(priority = 6, enabled = true)
	public void Search_for_Accommodations() throws InterruptedException {

		SetUp();
		// Choosing Random Destination

		WebElement DestinationField = driver.findElement(By.className("b915b8dc0b"));
		DestinationField.sendKeys(RandomEnglishCity);
		Thread.sleep(1000);
		WebElement TheListOfDestination = driver.findElement(By.className("e03644d405"));
		List<WebElement> Destinations = TheListOfDestination.findElements(By.tagName("li"));

		// Choosing The First Option In the List
		Destinations.get(0).click();
	}

	@Test(priority = 7, enabled = true)
	public void CheckInDate_CheckOutDate() {

		// Choosing Today's Date for Check_In And Tomorrow For Check_Out
		WebElement TodaysDateButton = driver.findElement(By.xpath("//span[@data-date='" + todayDate + "']"));
		TodaysDateButton.click();
		WebElement TomorrowsDateButton = driver.findElement(By.xpath("//span[@data-date='" + TommorowDate + "']"));
		TomorrowsDateButton.click();
	}

	@Test(priority = 8, enabled = true)
	public void Visitors_Rooms_Children() {
		// Choosing numbers Of Visitors,Rooms And Children
		WebElement VisitorsFielde = driver.findElement(By.className("ab2c86b370"));
		VisitorsFielde.click();
		List<WebElement> ListOfIncrementButtons = driver.findElements(
				By.cssSelector(".de576f5064.b46cd7aad7.e26a59bb37.c295306d66.c7a901b0e7.aaf9b6e287.dc8366caa6"));
		// Increase Number Of Adults to 3
		ListOfIncrementButtons.get(0).click();
		// Increase Number Of Children to 1
		ListOfIncrementButtons.get(1).click();
		// Increase Number Of Rooms to 2
		ListOfIncrementButtons.get(2).click();

		// Randomly Select The Child Age
		WebElement AgeOfTheChildSelectField = driver.findElement(By.className("ed4d3c8194"));
		Select Myselect = new Select(AgeOfTheChildSelectField);
		int NumberOfAgeOptions = (Myselect.getOptions().size()) - 1;
		int RandomAgeIndex = 1 + rand.nextInt(NumberOfAgeOptions);
		Myselect.selectByIndex(RandomAgeIndex);

		// Click On The Search Button
		WebElement SearchButton = driver.findElement(By.cssSelector(
				".de576f5064.b46cd7aad7.ced67027e5.dda427e6b5.e4f9ca4b0c.ca8e0b9533.cfd71fb584.a9d40b8d51"));
		SearchButton.click();

		// Checking If The Result is correct
		List<WebElement> ListOfLocationSearchResult = driver.findElements(By.xpath("//span[@data-testid='address']"));
		String FirstResultLocation = ListOfLocationSearchResult.get(0).getText();
		System.out.println(FirstResultLocation);
		System.out.println(RandomEnglishCity);

		Assert.assertTrue(FirstResultLocation.toLowerCase().contains(RandomEnglishCity.toLowerCase()));

		// Closing The Pop Up
		ClosingThePopUp();
	}

	@Test(priority = 9, enabled = true)
	public void NavigateToAccommodationDetails() throws InterruptedException {
		// Click on The First accommodation in results

		List<WebElement> ListOfSearchResult = driver.findElements(By.cssSelector(".b87c397a13.a3e0b4ffd1"));
		ListOfSearchResult.get(0).click();

		// Switching between Tabs
		Set<String> handels = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(handels);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(windowList.get(0));
		System.out.println(driver.getTitle());

	}

	@Test(priority = 10, enabled = true)
	public void setRandomPriceFilter() throws InterruptedException {
		List<WebElement> SLiders = driver.findElements(By.className("fc835e65e6"));
		WebElement sliderMin = SLiders.get(0);
		WebElement sliderMax = SLiders.get(1);

		// Safer random drag range since max is about 200
		int moveMinBy = 10 + rand.nextInt(40);
		int moveMaxBy = -(10 + rand.nextInt(40));
		move.clickAndHold(sliderMin).moveByOffset(moveMinBy, 0).release().perform();
		Thread.sleep(500);
		move.clickAndHold(sliderMax).moveByOffset(moveMaxBy, 0).release().perform();
		Thread.sleep(500);

	}

	@Test(priority = 11, enabled = true)
	public void sortByRating() throws InterruptedException {
		WebElement sortDropdown = driver.findElement(By.className("cd46a6a263"));
		sortDropdown.click();
		Thread.sleep(1000); // Wait for options to load
		// 2. Click the "Top Reviewed" option (bayesian_review_score)
		WebElement topReviewed = driver.findElement(By.xpath("//button[@data-id='bayesian_review_score']"));
		topReviewed.click();

	}

	@Test(priority = 12, enabled = true)

	public void BookingProcess() throws InterruptedException {
		Thread.sleep(1000);
		// Randomly Select on of The Results
		List<WebElement> Accommodations = driver.findElements(By.xpath("//div[@data-testid='property-card']"));
		int RandomAccommodationIndex = rand.nextInt(Accommodations.size());
		WebElement SeeAvailabilityButton = Accommodations.get(RandomAccommodationIndex)
				.findElement(By.className("d755458b0f"));
		Thread.sleep(1000);
		SeeAvailabilityButton.click();

		// Switching between Tabs
		Set<String> handels = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(handels);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		Thread.sleep(1000);

		WebElement Reserve = driver.findElement(By.id("hp_book_now_button"));
		Reserve.click();

		List<WebElement> AllRoomsSelect = driver.findElements(By.cssSelector(".hprt-nos-select.js-hprt-nos-select"));
		int RandomRoomSelectIndex = rand.nextInt(AllRoomsSelect.size());

		WebElement TheRoom = AllRoomsSelect.get(RandomRoomSelectIndex);

		Select RoomSelect = new Select(TheRoom);
		int NumOfOptions = RoomSelect.getOptions().size() - 1;
		int RandomNumOfRooms = rand.nextInt(NumOfOptions) + 1;

		RoomSelect.selectByIndex(RandomNumOfRooms);

		WebElement ReserveButton = driver.findElement(By.cssSelector(".b-button__from-text.book_now_rt_summary"));
		ReserveButton.click();
		Thread.sleep(3000);

	}

	@Test(priority = 13, enabled = true)
	public void PaymentDetails() throws InterruptedException {
		WebElement Firstname = driver.findElement(By.name("firstname"));
		WebElement Lastname = driver.findElement(By.name("lastname"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement phone = driver.findElement(By.name("phoneNumber"));
		WebElement NextToFinalDetailsButton = driver.findElement(By.name("book"));
		String TheFirstName = firstNames[FirstNameNumber];
		String TheLastName = lastNames[LastNameNumber];
		String Email = TheFirstName + TheLastName + "@gmail.com";
		Thread.sleep(1000);
		Firstname.sendKeys(TheFirstName);
		Thread.sleep(1000);

		Lastname.sendKeys(TheLastName);
		Thread.sleep(1000);

		email.sendKeys(Email);
		Thread.sleep(1000);

		phone.sendKeys(phoneNumbers[Numbers]);
		Thread.sleep(1000);

		if (driver.findElements(By.xpath("//input[@Name='address1']")).size() > 0
				&& driver.findElement(By.xpath("//input[@Name='address1']")).isDisplayed()) {

			WebElement AddressField = driver.findElement(By.xpath("//input[@Name='address1']"));
			AddressField.sendKeys(RandomEnglishCity);

			WebElement CityField = driver.findElement(By.xpath("//input[@Name='city']"));
			CityField.sendKeys(RandomEnglishCity);

		}

		NextToFinalDetailsButton.click();

	}

	@Test(priority = 14, enabled = true)
	public void FinishThePayment() throws InterruptedException, IOException {
		Thread.sleep(1000);

		// Case: If the element with ID ":r6:" is displayed, just click the book button
		if (driver.findElements(By.id(":r6:")).size() > 0 && driver.findElement(By.id(":r6:")).isDisplayed()) {
			driver.findElement(By.name("book")).click();
		} else {
			Thread.sleep(10000);

			// Check if the iframe with title 'Payment' is present before switching
			List<WebElement> paymentIframes = driver.findElements(By.cssSelector("iframe[title='Payment']"));
			if (!paymentIframes.isEmpty()) {
				driver.switchTo().frame(paymentIframes.get(0));
				JS.executeScript("window.scrollTo(0,500)");

				WebElement cardnumber = driver.findElement(By.name("number"));
				WebElement expdate = driver.findElement(By.name("expirationDate"));
				WebElement cvc = driver.findElement(By.name("cvc"));

				Thread.sleep(2000);
				cardnumber.sendKeys(CardNumber);
				Thread.sleep(2000);
				expdate.sendKeys(ExpDate);
				Thread.sleep(2000);
				cvc.sendKeys(cnn);

				driver.switchTo().defaultContent(); // switch back to the main content
			}

			// Proceed with clicking the book button after iframe handling
			driver.findElement(By.name("book")).click();
		}
		ScreenShot();

	}
}