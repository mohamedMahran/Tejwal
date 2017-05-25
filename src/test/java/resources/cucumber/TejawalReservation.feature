Feature: CreateAccount functinality
  As a user  I want to check functionality of Tejawal Home Page

  Background: 
    Given Customer Navigate to Tejawal Home Page

  @chrome
  Scenario: check that Client reserve a ticket for 2 adults in economy class
    When Client Enter Trip Information
	    And Press Search Flights
    Then the search query data will aligned with any specific carrier
    And Client Filter by Emirates Airways
    Then Lisitng results are only the selected carrier
    When Client Enter Contact Details
    And Press Continue to Payment
