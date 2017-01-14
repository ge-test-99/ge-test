package com.goeuro.Tests;


import com.goeuro.Pages.SearchPage;
import com.goeuro.Pages.SearchResultsTrainPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import java.util.List;

public class SearchResultsTest extends SeleniumTestBase{

    private SearchPage searchPage;
    private SearchResultsTrainPage resultsPage;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver);
        resultsPage = new SearchResultsTrainPage(driver);
        searchPage.goTo();
    }

    @Test
    public void siteAvailableTest() throws InvalidElementStateException {
        Assert.assertTrue(
                "Search page URL mismatch",
                searchPage.at()
        );
    }

    @Test
    public void priceSortingTest() throws InterruptedException {
        searchPage.search("Berlin", "Prague");
        Assert.assertTrue(
                "Train results page URL mismatch",
                resultsPage.at()
        );
        resultsPage.sortByPrice();
        List<Float> prices = resultsPage.getPrices();
        for(int i=0; i < prices.size()-1; i++){
            Assert.assertTrue(
                    "Wrong price sort order",
                    prices.get(i) <= prices.get(i+1)
            );
        }
    }
}
