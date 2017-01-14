package com.goeuro.Pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class SeleniumPageBase {

    private String baseURL = "http://www.goeuro.com";
    String path = "/";
    WebDriver driver;

    public SeleniumPageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumPageBase goTo(){
        this.driver.get(String.format("%s%s", baseURL, path));
        return this;
    }

    public boolean at() {
        return this.driver.getCurrentUrl().endsWith(path);
    }

    public void autoFill(WebElement input, String text){
        String filled;
        input.sendKeys(text);
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.RETURN);
        for(int i=0; i<10; i+=1){
            filled = input.getAttribute("value");
            if(!Objects.equals(text, filled))
                break;
            try {
                sleep(300);
            }
            catch (InterruptedException ignored){
            }
        }
    }
}
