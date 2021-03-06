package com.timecamp.steps.cucumber;

import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;


import java.util.Map;

public class CheckTimesheetDailyMainFunctions {


    @Steps
    private TimesheetPage timesheetPage;

    @Given("^user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete$")
    public void userIsOnTimesheetWhereIsNoTimeEntries() {
        timesheetPage.isTimesheetDailyIfNotSwitchToDaily();
        timesheetPage.isTimerRunning();
        timesheetPage.isTotalDurationDisplayed();
    }

    @When ("^user start timer using main widget$")
    public void userStartTimerUsingMainWidget() {
        timesheetPage.clickStartTimerButton();
    }

    @And("^created project using popup widget and chooses project$")
    public void createdProjectUsingPopupWidgetAndChoosesProject(Map<String,String> data) {
        timesheetPage.jsAddTaskFromTimesheet(data.get("taskName"));
    }


    @And("^add manually time entries with duration using link under timesheet and chooses project$")
    public void addManuallyTimeEntriesUsingLinkUnderTimesheetAndChoosesProject(Map<String,String> data) {
        timesheetPage.addTaskFromLinkUnderTimesheet(data.get("durationTime"));
    }

    @And("^stop timer which we started before$")
    public void stopTimerWhichWeStartedBefore() {
        timesheetPage.clickTimerStopButton();
    }

    @And("^add manually time entries with duration using main widget and chooses project$")
    public void addManuallyTimeEntriesUsingMainWidgetAndChoosesProject(Map<String,String> data) {
        timesheetPage.addTimeEntryFromManualMode(data.get("taskName"), data.get("durationTime"));
    }


    @Then("^we are checking size of time entries on timesheet$")
    public void weAreCheckingProjectName() {
        timesheetPage.getTimeEntriesCount();
    }

    @And("^we are checking duration of time entries$")
    public void weAreCheckingDurationOfTimeEntries(Map<String,String> data) {
        timesheetPage.getNamesAndDurationOfEntries(data.get("totalEntryDuration"));
    }
}
