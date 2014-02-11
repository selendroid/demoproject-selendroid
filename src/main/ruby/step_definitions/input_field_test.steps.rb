require File.dirname(__FILE__) + '/../screens/InputFieldTestScreen.rb'

Given(/^the input field is enabled$/) do
  @input_field = InputFieldTestScreen.new
  @input_field.verify_enabled
end

When(/^I put Selendroid in the input field$/) do
  @input_field.type_selendroid
end

When(/^fetch the text$/) do
  @input_field.get_field_contents
end

Then(/^it should be Selendroid$/) do
  @input_field.verify_field_contents
end
