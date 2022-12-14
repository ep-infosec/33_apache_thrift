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

package org.apache.thrift.transport;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.channels.ServerSocketChannel;
import org.junit.jupiter.api.Test;

public class TestNonblockingServerSocket {

  @Test
  public void testSocketChannelBlockingMode() throws TTransportException {
    try (TNonblockingServerSocket nonblockingServer = new TNonblockingServerSocket(0)) {
      ServerSocketChannel socketChannel = nonblockingServer.getServerSocketChannel();
      assertFalse(socketChannel.isBlocking(), "Socket channel should be nonblocking");
    }
  }
}
