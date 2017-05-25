package com.utility.bdd;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "json:target/cucumber.json"},
		features = "src/test/java/resources/cucumber/"
		,glue={""}
		)

public class RunCukesTest { 
	
    
}

