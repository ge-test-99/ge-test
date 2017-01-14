package com.goeuro.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends SeleniumPageBase{

    @FindBy(id = "from_filter")
    private WebElement fromFilterInput;

    @FindBy(id = "to_filter")
    private WebElement toFilterInput;

    @FindBy(id = "search-form__submit-btn")
    private WebElement searchSubmitButton;

    @FindBy(className = "hotel-checkbox__airbnb")
    private WebElement hotelCheckBox;

    private boolean hotelCheckBoxChecked(){
        return !driver.findElements(
                By.cssSelector(".hotel-checkbox__airbnb span.custom.checkbox.checked")
        ).isEmpty();
    }

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String origin, String destination) {

        autoFill(fromFilterInput, origin);
        autoFill(toFilterInput, destination);

        if(hotelCheckBoxChecked())
            hotelCheckBox.click();

        searchSubmitButton.click();
    }
}
