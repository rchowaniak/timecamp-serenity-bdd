package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.*;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;
import java.util.List;



public class TimesheetPage extends PageObject {

    @FindBy(id = "time_menu_link")
    private WebElementFacade timesheetTopButton;

    @FindBy(xpath = "//div[@class='entry-summary-duration ng-binding']")
    private WebElementFacade totalDuration;

    @FindBy(xpath = "//div[@class='entry-summary-duration ng-binding']")
    private List<WebElementFacade> durationList;

    @FindBy(xpath = "//div[@class='btn btn-default btn-wide-padding ng-binding']")
    private WebElementFacade bulkEditIcon;

    @FindBy(xpath = "//input[contains(@ng-click,'addAllEntriesToBulkEdit(day)')]")
    private WebElementFacade checkboxBulkcheck;

    @FindBy(xpath = "//div[@class='ts-bulk-edit-row']//a[contains(text(),'Delete')]")
    private WebElementFacade bulkDeleteLink;

    @FindBy(xpath = "//button[@data-bb-handler='confirm']")
    private WebElementFacade confirmBulkDeleteButton;

    @FindBy(xpath = "//div[@class='bootbox modal fade bootbox-confirm in']")
    private WebElementFacade bootboxConfirm;

    @FindBy(css = "modal-backdrop fade")
    private WebElementFacade modalFade;

    @FindBy(id = "timer-start-button")
    private WebElementFacade timerStartButton;

    @FindBy(xpath = "//span[@title='Select task or project']")
    private WebElementFacade selectTaskHiperLink;

    @FindBy(id = "js-add-task-btn-widget")
    private WebElementFacade jsAddTaskWidget;

    @FindBy(id = "js-add-box-name")
    private WebElementFacade jsTaskNameInput;

    @FindBy(id = "tcTTTaskPickerBase")
    private WebElementFacade jsBoxTask;

    @FindBy(xpath = "//div[@data-original-title='Add new time entry']")
    private WebElementFacade addTaskLink;

    @FindBy(id = "js-add-box-add-btn")
    private WebElementFacade jsAddTaskButton;

    @FindBy(xpath = "//*[@id='lastUsedTasks']/li/a")
    private WebElementFacade recentlyUsedTask;

    @FindBy(xpath = "//input[contains(@tabindex,'109')]")
    private WebElementFacade durationEntryInput;

    @FindBy(xpath = "//span[contains(.,'Stop timer')]")
    private WebElementFacade timerStopButton;

    @FindBy(id = "upperBarManualButton")
    private WebElementFacade manualModeLink;

    @FindBy(xpath = "//input[@tabindex='2']")
    private WebElementFacade durationWidgetInput;

    @FindBy(id = "new-entry-task-picker")
    private WebElementFacade inputWhatDidYouWorkOn;

    @FindBy(xpath = "//a[@ng-if='canAddManually()']")
    private WebElementFacade mainWidgetAddManually;

    @FindBy(xpath = "//div[@class='panel panel-default pull-right entry-body']//div[@class='entry-title']")
    private List<WebElementFacade> listOfEntriesOnTimesheet;

    @FindBy(xpath = "//div[@class='panel panel-default pull-right entry-body']")
    private WebElementFacade getNamesOfEntries;

    @FindBy(xpath = "//div[contains(@title,'total time in all entries, excluding unassigned computer activities')]")
    private WebElementFacade summaryEntryDuration;

    @FindBy(id = "timer-task-picker")
    private WebElementFacade whatAreYouWorkingOn;

    @FindBy(xpath = "//button[@ng-click='onReload(true); synchronizeIntegrations()']")
    private WebElementFacade refreshButton;

    @FindBy(xpath = "//a[contains(.,'Reports')]")
    private WebElementFacade reportsSection;

    @FindBy(xpath = "//a[contains(.,'Summary')]")
    private WebElementFacade summaryReportPage;

    @FindBy(xpath = "//a[@title='Graphical timesheet']")
    private WebElementFacade weeklyTimesheetButton;

    @FindBy(xpath = "//button[contains(@class,'btn btn-xs btn-success ng-binding')]")
    private WebElementFacade startTimerWeeklyButton;

    @FindBy(xpath = "//div[@ng-if='hasTimer()']")
    private WebElementFacade activeTimerWeeklyButton;

    @FindBy(xpath = "//span[@ng-click='selectedDay == dayno && $event.stopPropagation()']")
    private WebElementFacade selectTaskRunningTimer;

    @FindBy(xpath = "//a[@event='tt_manage'][contains(.,'Projects')]")
    private WebElementFacade projectTopButton;

    @FindBy(xpath = "//div[@ng-click='toggleTimer(data)']")
    private WebElementFacade stopTimerWeekly;

    @FindBy (xpath = "//p[@class='duration-time ng-binding ng-scope']")
    private WebElementFacade durationOfDayWeeklyTimesheet;

    @FindBy(xpath = "//a[@ng-href='#/timesheets/timer']")
    private WebElementFacade dailyTimesheetButton;

    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);


    public void isTimesheetButtonDisplayed(boolean isTimesheet) {
        if (isTimesheet) {
            timesheetTopButton.shouldBeVisible();
        } else {
            timesheetTopButton.shouldNotBeVisible();
        }
    }

    public void isTotalDurationDisplayed() {
        if (durationList.size() != 1) {
            log.info("Total duration is not present, so there is not time entries");
            totalDuration.shouldNotBeVisible();
        } else {
            waitForAngularRequestsToFinish();
            bulkEditIcon.waitUntilPresent().waitUntilVisible().click();
            checkboxBulkcheck.waitUntilClickable().click();
            bulkDeleteLink.waitUntilClickable().click();
            confirmBulkDeleteButton.click();
            confirmBulkDeleteButton.waitUntilNotVisible();
            bootboxConfirm.waitUntilNotVisible();
        }
    }

    public void clickStartTimerButton() {
        bootboxConfirm.waitUntilNotVisible();
        modalFade.waitUntilNotVisible();
        waitForAngularRequestsToFinish();
        timerStartButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void jsAddTaskFromTimesheet(String taskName) {
        jsAddTaskWidget.waitUntilPresent().waitUntilClickable().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + taskName);
        jsTaskNameInput.waitUntilNotVisible();
        jsAddTaskWidget.waitUntilNotVisible();
    }

    public void addTaskFromLinkUnderTimesheet(String durationTime) {
        jsBoxTask.waitUntilNotVisible();
        jsTaskNameInput.waitUntilNotVisible();
        addTaskLink.waitUntilVisible().click();
        recentlyUsedTask.click();
        durationEntryInput.typeAndEnter(durationTime);
    }

    public void clickTimerStopButton() {
        timerStopButton.click();
    }

    public void addTimeEntryFromManualMode(String taskName, String durationTime) {
        if (manualModeLink.isVisible())
            manualModeLink.click();
        inputWhatDidYouWorkOn.click();
        jsAddTaskWidget.waitUntilVisible().waitUntilClickable().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + taskName);
        jsTaskNameInput.waitUntilNotVisible();
        durationWidgetInput.typeAndEnter(durationTime);
        mainWidgetAddManually.click();
    }

    public void getTimeEntriesCount() {
        log.info("Size of time entries " + listOfEntriesOnTimesheet.size());
        Assert.assertEquals(3, listOfEntriesOnTimesheet.size());
    }

    public void getNamesAndDurationOfEntries(String totalEntryDuration) {
        log.info("Names of time entries " + getNamesOfEntries.getText());
        log.info("Durations of three entries is " + summaryEntryDuration.getText());
        refreshButton.click();
        refreshButton.click();
        summaryEntryDuration.waitUntilVisible();
        Assert.assertEquals(totalEntryDuration, summaryEntryDuration.getText());
    }

    public void changePageToSummaryReport() {
        withAction().moveToElement(reportsSection).perform();
        summaryReportPage.waitUntilClickable().click();
    }

    public void changeViewToWeekly() {
        waitForAngularRequestsToFinish();
        weeklyTimesheetButton.waitUntilPresent().waitUntilClickable().click();
    }

    public void clickStartButtonOnWeekly() {
        waitForAngularRequestsToFinish();
        Scroll.to(startTimerWeeklyButton).andAlignToBottom();
        startTimerWeeklyButton.waitUntilClickable().click();
        startTimerWeeklyButton.waitUntilNotVisible();
        if (!jsAddTaskWidget.isCurrentlyVisible()) {
            selectTaskRunningTimer.waitUntilVisible().click();
        }
    }

    public void drawEntryInWeekly() {
        waitForAngularRequestsToFinish();
//        MoveMouse.to(projectTopButton)
//                .andThen(actions -> actions.moveByOffset(100,0).build().perform())
//                .andThen(actions -> actions.click(projectTopButton).build().perform());
//        //projectTopButton.click();
//        System.out.println("czy to się wykonało?");
    }

    public void clickStopTimerWeekly() {
        waitForAngularRequestsToFinish();
        stopTimerWeekly.waitUntilVisible().click();
    }

    public void checkTaskNameAndDuration() {
        waitForAngularRequestsToFinish();
        durationOfDayWeeklyTimesheet.shouldBeVisible();
        durationOfDayWeeklyTimesheet.containsText("s4");
        selectTaskRunningTimer.containsText("Testing task added from weekly view");
    }
    public void changeViewToDaily() {
        waitForAngularRequestsToFinish();
        dailyTimesheetButton.waitUntilPresent().waitUntilClickable().click();
    }
}
