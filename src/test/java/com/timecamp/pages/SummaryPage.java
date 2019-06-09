package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SummaryPage extends PageObject {

    @FindBy(xpath = "//button[contains(@id,'tool-bar-custom-date-btn')]")
    private WebElementFacade durationFilter;

    @FindBy(xpath = "//a[@ng-click='todayBtn()']")
    private WebElementFacade todayFilter;

    @FindBy (id = "filterState_people")
    private WebElementFacade peopleFilter;

    @FindBy (partialLinkText = " - YOU")
    private WebElementFacade peopleYouFilter;

    @FindBy(xpath = "//div[@class='range-date-picker-button-panel']")
    private WebElementFacade listDuration;

    @FindBy (id = "tcUserPickerBase")
    private WebElementFacade listUserPicker;

    @FindBy(xpath = "//div[@tabindex='3']")
    private WebElementFacade totalSummaryData;

    @FindBy (id = "time_menu_link")
    private WebElementFacade timesheetPageLink;

    public void selectFilterTimeFrameToToday(){
       withAction().moveToElement(durationFilter).click(durationFilter).build().perform();
       todayFilter.waitUntilClickable().click();
       listDuration.waitUntilNotVisible();
    }
    public void selectFilterPeopleToYou(){
        withAction().moveToElement(peopleFilter).click(peopleFilter).build().perform();
        peopleYouFilter.waitUntilClickable().click();
        listUserPicker.waitUntilNotVisible();
    }
    public void getNamesAndDurationOfEntriesInSummary(String totalSummaryReport){
        totalSummaryData.waitUntilVisible();
        Assert.assertEquals(totalSummaryReport, totalSummaryData.getText());
    }
    public void goIntoTimesheetPage(){
        timesheetPageLink.click();
    }

}