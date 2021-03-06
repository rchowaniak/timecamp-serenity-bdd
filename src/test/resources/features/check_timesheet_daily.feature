Feature: Check timesheet basic functions on daily view

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|xxxx@xxxx.com|
      |password|xxxxxx|
    Then we are checking that user are on Timesheet

@DAILY_TIMESHEET
  Scenario: On Timesheet user starting timer using main widget, add manual time entries from link under Timesheet, stop
    timer that actually has running, then add manual time entries from main widget.

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
    When user start timer using main widget
    And created project using popup widget and chooses project
      |taskName| Very important testing task|
    And add manually time entries with duration using link under timesheet and chooses project
      |durationTime|2h30m|
    And stop timer which we started before
    And add manually time entries with duration using main widget and chooses project
      |taskName| Not that important testing task|
      |durationTime|6h55m|
    Then we are checking size of time entries on timesheet
    And we are checking duration of time entries
      |totalEntryDuration|9h 25m|
    And user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
