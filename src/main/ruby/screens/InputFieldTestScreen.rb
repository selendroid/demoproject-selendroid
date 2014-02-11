class InputFieldTestScreen
  
  def initialize
    @inputField = $driver.find_element(:id, 'my_text_field')
  end
  
  def verify_enabled
    @inputField.enabled?.should == true
  end
    
  def type_selendroid
    @inputField.send_keys('Selendroid')
  end
    
  def get_field_contents
    @my_text = @inputField.text
  end
  
  def verify_field_contents
    @my_text.should == 'Selendroid'
  end
  
end  