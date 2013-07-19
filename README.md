Demo about how to use selendroid
=====

This project demonstrated about how the test automation tool [selendroid](http://selendroid.io) can be used to test native and hybrid Android apps.

Before you start
------
To get started please [setup first your system](http://selendroid.io/setup.html#systemRequirements) and if you want to use an emulator during test I recommend to read [this section](http://selendroid.io/setup.html#androidDevices).


Use Selendroid
-----
The project is based on maven and uses to run the tests TestNG.

Selendroid is now available in Maven central can be added in the dependency section of the ```pom.xml```:

```xml
<dependency>
	<groupId>io.selendroid</groupId>
	<version>0.4.2</version>
	<artifactId>selendroid-standalone</artifactId>
</dependency>
<dependency>
	<groupId>io.selendroid</groupId>
	<version>0.4.2</version>
	<artifactId>selendroid-client</artifactId>
</dependency>
```

The test itself you find find [here](https://github.com/DominikDary/demoproject-selendroid/blob/master/src/main/java/io/selendroid/demo/SelendroidIntegrationTest.java).

Before the test is executed the selendroid-standalone server will be started:

```java
SelendroidConfiguration config = new SelendroidConfiguration();
config.addSupportedApp("src/main/resources/selendroid-test-app-0.4.2.apk");
selendroidServer = new SelendroidLauncher(config);
selendroidServer.lauchSelendroid();
```

If the server is started the ```SelendroidDriver```is initialized:

```java
SelendroidCapabilities caps = SelendroidCapabilities
.emulator("io.selendroid.testapp:0.4.2");
driver = new SelendroidDriver("http://localhost:5555/wd/hub", caps);
```

The Test
---

For testing the Android app the [webdriver API](http://docs.seleniumhq.org/docs/03_webdriver.jsp) is used:

```java
WebElement inputField = driver.findElement(By.id("my_text_field"));
inputField.sendKeys("Selendroid");
Assert.assertEquals("Selendroid", inputField.getText());
```

The test is about 

* finding the text field, 
* entering a text and 
* checking that the text was entered successfully.

For more details about selendroid please read the documentation [http://selendroid.io](http://selendroid.io).