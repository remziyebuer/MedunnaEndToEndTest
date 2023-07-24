@e2e @db
Feature: Room Database Test
  Scenario: Select Room
    Given connect to Database
    Then read room and validate
