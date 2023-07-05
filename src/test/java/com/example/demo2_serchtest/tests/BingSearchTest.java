package com.example.demo2_serchtest.tests;

import com.example.demo2_serchtest.pageg.MainPage;
import com.example.demo2_serchtest.pageg.ResultsPage;
import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        MainPage mp =  new MainPage(driver);
        mp.sendText(input);
        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Не совпадает");
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);
        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
}