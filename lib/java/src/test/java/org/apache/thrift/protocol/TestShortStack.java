/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.thrift.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestShortStack {

  @Test
  public void testOps() throws Exception {
    ShortStack s = new ShortStack(1);
    s.push((short) 10);
    s.push((short) 11);
    s.push((short) 12);
    assertEquals((short) 12, s.pop());
    assertEquals((short) 11, s.pop());
    s.push((short) 40);
    assertEquals((short) 40, s.pop());
    assertEquals((short) 10, s.pop());
    assertThrows(Exception.class, s::pop);
  }
}
