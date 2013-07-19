package io.selendroid.demo;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelendroidIntegrationTest {
	private SelendroidLauncher selendroidServer = null;
	private WebDriver driver = null;

	@Test
	public void testShouldBeAbleToEnterText() {
		WebElement inputField = driver.findElement(By.id("my_text_field"));
		Assert.assertEquals("true", inputField.getAttribute("enabled"));
		inputField.sendKeys("Selendroid");
		Assert.assertEquals("Selendroid", inputField.getText());
	}

	@BeforeClass
	protected void startSelendroidServer() throws Exception {
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
		SelendroidConfiguration config = new SelendroidConfiguration();
		config.addSupportedApp("src/main/resources/selendroid-test-app-0.4.2.apk");
		selendroidServer = new SelendroidLauncher(config);
		selendroidServer.lauchSelendroid();

		SelendroidCapabilities caps = SelendroidCapabilities
				.emulator("io.selendroid.testapp:0.4.2");

		driver = new SelendroidDriver("http://localhost:5555/wd/hub", caps);
	}

	@AfterClass
	protected void stopSelendroidServer() {
		if (driver != null) {
			driver.quit();
		}
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
	}

}
