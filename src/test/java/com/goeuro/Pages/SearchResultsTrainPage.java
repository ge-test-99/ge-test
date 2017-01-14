package com.goeuro.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsTrainPage extends SeleniumPageBase{

    private final String sortByPriceTabXPath =
            "//span[@data-key='dw.sorting.price']";

    @FindBy(xpath = sortByPriceTabXPath)
    private WebElement sortByPriceTab;

    public SearchResultsTrainPage(WebDriver driver) {
        super(driver);
        path = "/train";
    }

    public void sortByPrice(){

        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(sortByPriceTabXPath), 1));

        sortByPriceTab.click();
    }

    public List<Float> getPrices(){

        String priceElementsMainXPath = "//div[@data-test='Result']//span[contains(@class,'Result__priceMain')]";
        String priceElementsFractionXPath = "//div[@data-test='Result']//span[contains(@class,'Result__priceFraction')]";

        List<Float> prices = new ArrayList<Float>();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath(priceElementsMainXPath), 3));

        List<WebElement> priceElementsMain = driver.findElements(By.xpath(priceElementsMainXPath));
        List<WebElement> priceElementsFraction = driver.findElements(By.xpath(priceElementsFractionXPath));

        for(int i=0; i < priceElementsMain.size(); i++)
            prices.add(Float.parseFloat(String.format("%s.%s",
                    priceElementsMain.get(i).getText(),
                    priceElementsFraction.get(i).getText())));
        return prices;
    }
}
