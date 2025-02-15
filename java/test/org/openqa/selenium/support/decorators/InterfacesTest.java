// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.support.decorators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;

@Tag("UnitTests")
public class InterfacesTest {

  protected interface SomeOtherInterface {}

  protected interface ExtendedDriver extends WebDriver, SomeOtherInterface {}

  @Test
  public void shouldNotAddInterfacesNotAvailableInTheOriginalDriver() {
    WebDriver driver = mock(WebDriver.class);
    assertThat(driver).isNotInstanceOf(SomeOtherInterface.class);

    WebDriver decorated = new WebDriverDecorator().decorate(driver);
    assertThat(decorated).isNotInstanceOf(SomeOtherInterface.class);
  }

  @Test
  public void shouldRespectInterfacesAvailableInTheOriginalDriver() {
    WebDriver driver = mock(ExtendedDriver.class);
    assertThat(driver).isInstanceOf(SomeOtherInterface.class);

    WebDriver decorated = new WebDriverDecorator().decorate(driver);
    assertThat(decorated).isInstanceOf(SomeOtherInterface.class);
  }
}
