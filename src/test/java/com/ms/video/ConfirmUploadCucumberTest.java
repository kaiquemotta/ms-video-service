package com.ms.video;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.ms.video.steps",
        plugin = {"pretty"}
)
public class ConfirmUploadCucumberTest {
}
