/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.thrift.transport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;

public class TestTIOStreamTransport {
  // THRIFT-5022
  @Test
  public void testOpenClose_2streams() throws TTransportException {
    byte[] dummy = {20}; // So the input stream isn't EOF immediately.
    InputStream input = new ByteArrayInputStream(dummy);
    OutputStream output = new ByteArrayOutputStream();
    TTransport transport = new TIOStreamTransport(input, output);
    runOpenClose(transport);
  }

  // THRIFT-5022
  @Test
  public void testOpenClose_1input() throws TTransportException {
    byte[] dummy = {20};
    InputStream input = new ByteArrayInputStream(dummy);
    TTransport transport = new TIOStreamTransport(input);
    runOpenClose(transport);
  }

  // THRIFT-5022
  @Test
  public void testIOpenClose_1output() throws TTransportException {
    OutputStream output = new ByteArrayOutputStream();
    TTransport transport = new TIOStreamTransport(output);
    runOpenClose(transport);
  }

  private void runOpenClose(TTransport transport) throws TTransportException {
    transport.open();
    boolean b1 = transport.isOpen();
    assertTrue(b1);
    transport.close();
    boolean b2 = transport.isOpen();
    assertFalse(b2);
  }
}
