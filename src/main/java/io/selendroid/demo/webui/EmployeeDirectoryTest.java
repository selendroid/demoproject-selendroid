/*
 * Copyright 2012-2013 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.demo.webui;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Base Test to demonstrate how to test hybrid android apps with Selendroid. App under test is:
 * <code>src/main/resources/employee-directory.apk</code> Please make sure that you start a
 * selendroid-standalone with above mentioned apk.
 * 
 * @author ddary
 */
public class EmployeeDirectoryTest {
  private WebDriver driver = null;

  @Before
  public void setup() throws Exception {
    driver = new SelendroidDriver(new SelendroidCapabilities("io.selendroid.directory:0.0.1"));
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @Test
  public void assertEmployeeCanBeDisplayedWithDirects() throws Exception {
    driver.get("and-activity://io.selendroid.directory.EmployeeDirectory");

    // Switch to the web view context
    driver.switchTo().window("WEBVIEW");

    String vpOfEngineering = "John Williams";
    driver.findElement(By.tagName("input")).sendKeys(vpOfEngineering);
    driver.findElement(By.partialLinkText(vpOfEngineering)).click();
    Assert.assertEquals(driver.getCurrentUrl(), "file:///android_asset/www/index.html#employees/4");

    // Verify Manager
    Assert.assertThat(driver.findElements(By.tagName("li")).get(0).getText(),
        endsWith("James King"));

    // Verify number of direct reports
    WebElement directs = driver.findElements(By.tagName("li")).get(1);
    Assert.assertThat(directs.getText(), endsWith("3"));
    directs.click();
    Assert.assertEquals(driver.getCurrentUrl(),
        "file:///android_asset/www/index.html#employees/4/reports");

    // Verify directs by name
    Assert.assertThat(driver.findElements(By.tagName("li")).get(0).getText(),
        startsWith("Paul Jones"));
    Assert.assertThat(driver.findElements(By.tagName("li")).get(1).getText(),
        startsWith("Paula Gates"));
    Assert.assertThat(driver.findElements(By.tagName("li")).get(2).getText(),
        startsWith("Steven Wells"));

    driver.navigate().back();

    Assert.assertEquals(driver.getCurrentUrl(), "file:///android_asset/www/index.html#employees/4");
  }

  @After
  public void teardown() {
    driver.quit();
  }
}
