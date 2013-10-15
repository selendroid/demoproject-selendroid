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

public class UserDO {
  public String username;
  public String email;
  public String password;
  public String name;
  public String programmingLanguage;

  public UserDO(String username, String email, String password, String name,
      String programmingLanguage) {
    super();
    this.username = username;
    this.email = email;
    this.password = password;
    this.name = name;
    this.programmingLanguage = programmingLanguage;
  }
}
