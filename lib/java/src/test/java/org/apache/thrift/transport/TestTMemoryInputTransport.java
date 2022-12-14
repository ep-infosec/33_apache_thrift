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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestTMemoryInputTransport {
  @Test
  public void testFresh() throws Exception {
    byte[] input_buf = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TMemoryInputTransport trans = new TMemoryInputTransport(input_buf);
    assertEquals(0, trans.getBufferPosition());
    assertEquals(input_buf, trans.getBuffer());
    assertEquals(10, trans.getBytesRemainingInBuffer());

    byte[] buf1 = new byte[4];
    trans.readAll(buf1, 0, 4);
    assertArrayEquals(new byte[] {1, 2, 3, 4}, buf1);
    assertEquals(4, trans.getBufferPosition());
    assertEquals(6, trans.getBytesRemainingInBuffer());

    trans.consumeBuffer(2);

    assertEquals(6, trans.getBufferPosition());
    assertEquals(4, trans.getBytesRemainingInBuffer());

    trans.readAll(buf1, 0, 4);
    assertArrayEquals(new byte[] {7, 8, 9, 10}, buf1);
    assertEquals(10, trans.getBufferPosition());
    assertEquals(0, trans.getBytesRemainingInBuffer());
  }

  @Test
  public void testReused() throws Exception {
    byte[] input_buf = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TMemoryInputTransport trans = new TMemoryInputTransport(input_buf);
    assertEquals(0, trans.getBufferPosition());
    assertEquals(input_buf, trans.getBuffer());
    assertEquals(10, trans.getBytesRemainingInBuffer());

    byte[] new_buf = new byte[] {10, 9, 8};
    trans.reset(new_buf);
    assertEquals(0, trans.getBufferPosition());
    assertEquals(new_buf, trans.getBuffer());
    assertEquals(3, trans.getBytesRemainingInBuffer());
  }

  @Test
  public void testWithOffsetAndLength() throws TTransportException {
    byte[] input_buf = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TMemoryInputTransport trans = new TMemoryInputTransport(input_buf, 1, 3);
    assertEquals(1, trans.getBufferPosition());
    assertEquals(3, trans.getBytesRemainingInBuffer());

    {
      byte[] readBuffer = new byte[3];
      trans.readAll(readBuffer, 0, 3);
      assertArrayEquals(new byte[] {2, 3, 4}, readBuffer);
      assertThrows(Exception.class, () -> trans.readAll(readBuffer, 0, 3));
    }

    {
      trans.reset(input_buf, 3, 4);
      byte[] readBuffer2 = new byte[4];
      trans.readAll(readBuffer2, 0, 4);
      assertArrayEquals(new byte[] {4, 5, 6, 7}, readBuffer2);
    }
  }
}
