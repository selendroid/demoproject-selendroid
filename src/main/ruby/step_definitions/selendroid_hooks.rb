require 'selenium-webdriver'
require 'rspec/expectations'

Before do |scenario|
  capabilities = 
    {
      "aut" => 'io.selendroid.testapp:0.9.0'
    }    
    $driver = Selenium::WebDriver.for(:remote, :desired_capabilities => capabilities)
    $driver.manage.timeouts.implicit_wait = 30
end

After do |scenario|
  $driver.quit
end
