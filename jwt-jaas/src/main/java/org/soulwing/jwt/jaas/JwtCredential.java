/*
 * File created on Feb 15, 2019
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
package org.soulwing.jwt.jaas;

import org.soulwing.jwt.api.UserPrincipal;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * An object that holds a {@link DecodedJWT}.
 *
 * @author Carl Harris
 */
public interface JwtCredential {

  /**
   * Gets the decoded JWT object.
   * @return decoded JWT (never {@code null})
   */
  DecodedJWT getToken();

  /**
   * Gets the associated user principal.
   * @return
   */
  UserPrincipal getPrincipal();

}
