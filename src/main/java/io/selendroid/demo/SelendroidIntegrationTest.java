/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
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
package io.selendroid.demo;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelendroidIntegrationTest {
  private static SelendroidLauncher selendroidServer = null;
  private static WebDriver driver = null;

  @Test
  public void testShouldBeAbleToEnterText() {
    WebElement inputField = driver.findElement(By.id("my_text_field"));
    inputField.sendKeys("Selendroid");
    Assert.assertEquals("Selendroid", inputField.getText());
  }

  @BeforeClass
  public static void startSelendroidServer() throws Exception {
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
    SelendroidConfiguration config = new SelendroidConfiguration();
    config.addSupportedApp("src/main/resources/selendroid-test-app-0.10.0.apk");
    selendroidServer = new SelendroidLauncher(config);
    selendroidServer.launchSelendroid();

    SelendroidCapabilities caps =
        new SelendroidCapabilities("io.selendroid.testapp:0.10.0");

    driver = new SelendroidDriver(caps);
  }

  @AfterClass
  public static void stopSelendroidServer() {
    if (driver != null) {
      driver.quit();
    }
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
  }

}
