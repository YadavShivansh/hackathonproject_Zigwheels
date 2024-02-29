package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.excelUtils;

public class usedCars extends BasePage {

	public usedCars(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	String xlpath = System.getProperty("user.dir") + "\\src\\test\\resources\\Output.xlsx";

	@FindBy(xpath = "//a[@class='c-p'][normalize-space()='Used Cars']")
	public WebElement used_Cars;

	@FindBy(xpath = "//ul[@class='h-d-dd h-col-1']")
	public WebElement usedCardrpdown;

	@FindBy(xpath = "//input[@id='headerSearch']")
	public WebElement searchbox;

	@FindBy(xpath = "//ul[@id='ui-id-1']")
	public WebElement searchOption;

	// Storing list of Used cars Locations
	@FindBy(xpath = "//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[7]/ul/li/div[2]/ul/li")
	public List<WebElement> carsLocation;

	@FindBy(xpath = "(//a[contains(text(),'Used Cars')])[1]")
	public WebElement usedCar;

	@FindBy(xpath = "//div[@class=\"h-dd-r\"]//ul//li")
	public List<WebElement> location;

	// @FindBy(xpath = "//span[@class='uFm text-center zw-cmn-cursorPointer']")
	@FindBy(xpath = "//div[normalize-space(text())='Popular Models']")
	public WebElement scrollCar;

	@FindBy(xpath = "//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']//li")
	public List<WebElement> brands;

	@FindBy(xpath = "//input[@class='carmmCheck']")
	public List<WebElement> selectBrnds;

	@FindBy(xpath = "//a[@class='fnt-22 zm-cmn-colorBlack n zw-sr-headingPadding clickGtm saveslotno']")
	public List<WebElement> carsName;

	@FindBy(xpath = "//span[@class='zw-cmn-price n pull-left mt-3']")
	public List<WebElement> carsPrice;

	@FindBy(xpath = "//span[@class='zc-7 fnt-14 uLm block']")
	public List<WebElement> carsCity;

	@FindBy(xpath = "//*[@id='thatsAllFolks']")
	public WebElement endCars;

	public void hoverUsedCar() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOf(usedCar));
		Actions hover1 = new Actions(driver);
		hover1.moveToElement(usedCar).build().perform();

	}

	public void cheakdrpDown() {
		usedCardrpdown.isDisplayed();
	}

	public void clickSearch() {
		searchbox.click();
	}

	public void searchOptions() {
		searchOption.isDisplayed();
	}

	public List<WebElement> selectLoc() {
		return location;
	}

	public void scroll_to_popularBrands() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOf(scrollCar));
		js.executeScript("arguments[0].scrollIntoView;", scrollCar);
	}

	public void extractCarDetails() throws InterruptedException, IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < brands.size(); i++) {
			String models = brands.get(i).getText();
			excelUtils.setCellData(xlpath, "CarDetails", i + 1, 0, models);
		}

		for (int i = 0; i < brands.size(); i++) {

			js.executeScript("arguments[0].click();", selectBrnds.get(i));
			// Thread.sleep(1000);
			// js.executeScript("arguments[0].scrollIntoView();", endCars);
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(1000);
			String model = brands.get(i).getText();

			for (int j = 0; j < carsName.size(); j++) {

				try {
					System.out.println(j);
					excelUtils.setCellData(xlpath, model, j + 1, 0, carsName.get(j).getText());
					excelUtils.setCellData(xlpath, model, j + 1, 1, carsPrice.get(j).getText());
					excelUtils.setCellData(xlpath, model, j + 1, 2, carsCity.get(j).getText());
				}

				catch (Exception e) {

				}

				// Thread.sleep(3000);

			}

			js.executeScript("arguments[0].scrollIntoView();", scrollCar);
			js.executeScript("arguments[0].click();", selectBrnds.get(i));

		}

	}

}