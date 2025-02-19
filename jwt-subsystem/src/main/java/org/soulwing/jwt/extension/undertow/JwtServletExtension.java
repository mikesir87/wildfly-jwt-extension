/*
 * File created on Apr 7, 2019
 *
 * Copyright (c) 2019 Carl Harris, Jr
 * and others as noted
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.soulwing.jwt.extension.undertow;

import static org.soulwing.jwt.extension.undertow.UndertowLogger.LOGGER;

import java.util.function.Supplier;
import javax.servlet.ServletContext;

import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StopContext;
import org.soulwing.jwt.extension.service.AuthenticationService;
import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;

/**
 * A {@link ServletExtension} that replaces the deployment's authentication
 * mechanism with the JWT authentication mechanism.
 */
public class JwtServletExtension
    implements Service<ServletExtension>, ServletExtension {

  private Supplier<AuthenticationService> authenticationService;

  public void setAuthenticationService(
      Supplier<AuthenticationService> authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  public ServletExtension getValue()
      throws IllegalStateException, IllegalArgumentException {
    return this;
  }

  @Override
  public void start(StartContext startContext) {
    LOGGER.info(startContext.getController().getName() + " started");
  }

  @Override
  public void stop(StopContext stopContext) {
    LOGGER.info(stopContext.getController().getName() + " stopped");
  }

  @Override
  public void handleDeployment(DeploymentInfo deploymentInfo, 
      ServletContext servletContext) {

    deploymentInfo.clearLoginMethods();
    deploymentInfo.addFirstAuthenticationMechanism(
        JwtAuthenticationMechanism.MECHANISM_NAME,
        new JwtAuthenticationMechanism(
            deploymentInfo.getIdentityManager(), authenticationService));
  }

}
