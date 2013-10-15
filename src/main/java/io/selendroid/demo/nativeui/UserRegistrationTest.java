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
package io.selendroid.demo.nativeui;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Base Test to demonstrate how to test native android apps with Selendroid.
 * App under test is: src/main/resources/selendroid-test-app-0.5.1.apk
 * 
 * @author ddary
 */
public class UserRegistrationTest {
  private WebDriver driver = null;

  @Before
  public void setup() throws Exception {
    driver =
        new SelendroidDriver("http://localhost:5555/wd/hub",
            SelendroidCapabilities.device("io.selendroid.testapp:0.5.1"));
  }

  @Test
  public void assertUserAccountCanRegistered() throws Exception {
    // Initialize test data
    UserDO user = new UserDO("u$erNAme", "me@myserver.com", "mySecret", "John Doe", "Python");

    registerUser(user);
    verifyUser(user);
  }

  private void registerUser(UserDO user) throws Exception {
    driver.get("and-activity://io.selendroid.testapp.RegisterUserActivity");

    WebElement username = driver.findElement(By.id("inputUsername"));
    username.sendKeys(user.username);

    driver.findElement(By.id("inputEmail")).sendKeys(user.email);
    driver.findElement(By.id("inputPassword")).sendKeys(user.password);

    WebElement nameInput = driver.findElement(By.id("inputName"));
    Assert.assertEquals(nameInput.getText(), "Mr. Burns");
    nameInput.clear();
    nameInput.sendKeys(user.name);

    driver.findElement(By.id("input_preferedProgrammingLanguage")).click();
    driver.findElement(By.linkText(user.programmingLanguage)).click();

    driver.findElement(By.id("input_adds")).click();

    driver.findElement(By.id("btnRegisterUser")).click();
    Assert.assertEquals(driver.getCurrentUrl(), "and-activity://VerifyUserActivity");
  }

  private void verifyUser(UserDO user) throws Exception {
    Assert.assertEquals(driver.findElement(By.id("label_username_data")).getText(), user.username);
    Assert.assertEquals(driver.findElement(By.id("label_email_data")).getText(), user.email);
    Assert.assertEquals(driver.findElement(By.id("label_password_data")).getText(), user.password);
    Assert.assertEquals(driver.findElement(By.id("label_name_data")).getText(), user.name);
    Assert.assertEquals(driver.findElement(By.id("label_preferedProgrammingLanguage_data"))
        .getText(), user.programmingLanguage);
    Assert.assertEquals(driver.findElement(By.id("label_acceptAdds_data")).getText(), "true");
  }

  @After
  public void teardown() {
    driver.quit();
  }
}
