# Demo about how to use selendroid


This project demonstrats about how to use the test automation tool [selendroid](http://selendroid.io) that can be used to test native and hybrid Android apps.

## Before you start

To get started please [setup first your system](http://selendroid.io/setup.html#systemRequirements) and if you want to use an emulator during test I recommend to read [this section](http://selendroid.io/setup.html#androidDevices).


## Use Selendroid with Maven

The project is based on maven and uses to run the tests TestNG.

Selendroid is now available in Maven central can be added in the dependency section of the ```pom.xml```:

```xml
<dependency>
	<groupId>io.selendroid</groupId>
	<version>0.5.1</version>
	<artifactId>selendroid-standalone</artifactId>
</dependency>
<dependency>
	<groupId>io.selendroid</groupId>
	<version>0.5.1</version>
	<artifactId>selendroid-client</artifactId>
</dependency>
```

Before starting ```SelendroidDriver``` it is mandatory to start the ```SelendroidStandalone```-server. This can be done by

* using the command line or
* code in the test case

### Command line start of selendroid-standalone 

Go through Selendroid's [setup instructions](http://selendroid.io/setup.html) and download the current version. The applications that are we are using in this project are located in [this folder](https://github.com/selendroid/demoproject-selendroid/tree/master/src/main/resources).

```
java -jar selendroid-standalone-0.5.1-with-dependencies.jar -aut EmloyeeDirectory.apk -aut selendroid-test-app-0.5.1.apk
```

When the server is started you can verify the current configuration [http://localhost:5555/wd/hub/status](http://localhost:5555/wd/hub/status):

```
"supportedApps":[
	{
		"appId":"io.selendroid.directory:0.0.1",
		"mainActivity":"io.selendroid.directory.EmployeeDirectory",
		"basePackage":"io.selendroid.directory"},
	{
		"appId":"io.selendroid.testapp:0.5.1",
		"mainActivity":"io.selendroid.testapp.HomeScreenActivity",
		"basePackage":"io.selendroid.testapp"
	}
]

```

### Start selendroid-standalone during test


Before the test is executed the selendroid-standalone server will be started:

```java
SelendroidConfiguration config = new SelendroidConfiguration();
// Add the selendroid-test-app to the standalone server
config.addSupportedApp("src/main/resources/selendroid-test-app-0.5.1.apk");
selendroidServer = new SelendroidLauncher(config);
selendroidServer.lauchSelendroid();
```
The test itself you find find [here](https://github.com/selendroid/demoproject-selendroid/blob/master/src/main/java/io/selendroid/demo/SelendroidIntegrationTest.java#L45).



# Tests

After the ```selendroid-standalone``` has been started, the ```SelendroidDriver```can be initialized:

```java
// Create the selendroid capabilities and specify to use an emulator and selendroid's test app
SelendroidCapabilities caps = SelendroidCapabilities.emulator("io.selendroid.testapp:0.5.1");
driver = new SelendroidDriver("http://localhost:5555/wd/hub", caps);
```

The capabilities are describing what app is used for testing and in this example the selendroid test app is used. 

## Simple Tests

For testing the Android app the [webdriver API](http://docs.seleniumhq.org/docs/03_webdriver.jsp) is used. This demonstrates about how a text field can be foud and text can be entered.

```java
// Find an element by id
WebElement inputField = driver.findElement(By.id("my_text_field"));
//enter a text into the text field
inputField.sendKeys("Selendroid");
//check if the text has been entered into the text field
Assert.assertEquals("Selendroid", inputField.getText());
```

## Native App Test

Selendroid's [test app](https://github.com/selendroid/demoproject-selendroid/blob/master/src/main/resources/selendroid-test-app-0.5.1.apk) contains an user registration flow that will be tested in the [UserRegistrationTest](https://github.com/selendroid/demoproject-selendroid/blob/master/src/main/java/io/selendroid/demo/nativeui/UserRegistrationTest.java).

The test demonstrates how activities can be started and how the interaction with different elements can be done.

## Hybrid App Test

Selendroid can be used to test hybrid applications. The project contains a [cordova sample app](https://github.com/selendroid/demoproject-selendroid/blob/master/src/main/resources/employee-directory.apk) and the [EmployeeDirectoryTest](https://github.com/selendroid/demoproject-selendroid/blob/master/src/main/java/io/selendroid/demo/webui/EmployeeDirectoryTest.java) demonstrates about how web views can be tested.

# More Details?
More details about selendroid can be found in the  [documentation](http://selendroid.io).
