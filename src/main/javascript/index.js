var webdriver = require('selenium-webdriver'),
    SeleniumServer = require('selenium-webdriver/remote').SeleniumServer,
    assert = require('assert'),
    //change the next two vars to match your filesystem
    selendroidPath = '/usr/local/bin/selendroid-standalone.jar', //symbolic linked to selendroid-standalone-0.12.0-with-dependencies.jar
    appPath = '/Users/medelman/src/android/selendroid-test-app-0.12.0.apk',
    caps = {
        browserName: 'android',
        aut: 'io.selendroid.testapp:0.12.0'
    },
    driver = new webdriver.Builder().
        withCapabilities(caps).
        usingServer(getServer()).
        build();
function getServer() {
    var server = new SeleniumServer(selendroidPath, {
        port: 4444,
        stdio: 'inherit', //remove this if you don't want to see the selendroid process stdout
        args: ['-app', '/Users/medelman/src/android/selendroid-test-app-0.12.0.apk']
    });
    server.start();
    return server.address();
}
driver.get('and-activity://io.selendroid.testapp.HomeScreenActivity');
driver.getCurrentUrl().then(function (currentUrl) {
    assert.equal(currentUrl, 'and-activity://HomeScreenActivity')
});
driver.findElement(webdriver.By.id('my_text_field')).then(function (myTextField) {
    myTextField.sendKeys('Hello Selendroid');
    return myTextField.getText();
}).then(function (text) {
	assert.equal(text, 'Hello Selendroid');
});

driver.quit();
