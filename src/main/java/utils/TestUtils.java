package utils;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class TestUtils extends TestBase {

    public static int IMPLICITELY_WAIT = 10;
    public static int PAGELOAD_TIMEOUT = 10;
    public static int WAIT_TIME = 10;

    public static String TESTDATA_FLIGHT_SOURCE = "Mumbai";
    public static String TESTDATA_FLIGHT_DESTINATION = "Kochi";
    public static String TESTDATA_ADULT_COUNT = "2";
    public static String TESTDATA_CHILDREN_COUNT = "1";
    public static String TESTDATA_TRAVEL_CLASS = "Business";

    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/testData/goibiboTestData.xlsx";
    static Workbook book;
    static Sheet sheet;

    //take screenshot
    public static void takeScreenshotAtEndOfTest() throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");

        FileUtils.copyFile(srcFile, new File(currentDir + "/screenshot/" + System.currentTimeMillis() + ".png"));
    }

    //wait for element to be clickable
    public static void waitForElement(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //wait for element to be clickable and click the element
    public static void waitForElementAndClick(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //get date
    public static String enterDate(int plusDay) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(plusDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String departureDate = date.format(formatter);
        return departureDate;
    }

    //element click using JavascriptExecutor
    public static void jsExecutorClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //scroll into view using JavascriptExecutor
    public static void jsExecutorScrollToView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //get text using JavascriptExecutor
    public static void jsGetText(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //get text
    public static String getData(WebElement element) {
        return element.getText();
    }

    //get test data from excel file
    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }

}
