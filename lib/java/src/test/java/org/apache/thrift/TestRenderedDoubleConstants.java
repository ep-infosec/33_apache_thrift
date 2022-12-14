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

package org.apache.thrift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import thrift.test.DoubleConstantsTestConstants;

public class TestRenderedDoubleConstants {
  private static final double EPSILON = 0.0000001;
  private static final String ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST =
      "failed to verify a double constant generated by Thrift (expected = %f, got = %f)";
  private static final String ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_LIST_TEST =
      "failed to verify a list item by Thrift (expected = %f, got = %f)";
  private static final String ASSERTION_MESSAGE_FOR_TYPE_CHECKS =
      "the rendered variable with name %s is not of double type";

  // to make sure lists containing doubles are generated correctly
  @Test
  public void testRenderedDoubleList() throws Exception {
    final double[] EXPECTED_LIST = {
      1d,
      -100d,
      100d,
      9223372036854775807d,
      -9223372036854775807d,
      3.14159265359,
      1000000.1,
      -1000000.1,
      1.7e+308,
      -1.7e+308,
      9223372036854775816.43,
      -9223372036854775816.43
    };
    assertEquals(EXPECTED_LIST.length, DoubleConstantsTestConstants.DOUBLE_LIST_TEST.size());
    for (int i = 0; i < EXPECTED_LIST.length; ++i) {
      assertEquals(
          EXPECTED_LIST[i],
          DoubleConstantsTestConstants.DOUBLE_LIST_TEST.get(i),
          EPSILON,
          String.format(
              ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_LIST_TEST,
              EXPECTED_LIST[i],
              DoubleConstantsTestConstants.DOUBLE_LIST_TEST.get(i)));
    }
  }

  // to make sure the variables inside Thrift files are generated correctly
  @Test
  public void testRenderedDoubleConstants() throws Exception {
    final double EXPECTED_DOUBLE_ASSIGNED_TO_INT_CONSTANT = 1.0;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT = -100.0;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT = 9223372036854775807.0;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT = -9223372036854775807.0;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS = 3.14159265359;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE = 1000000.1;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE = -1000000.1;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_DOUBLE = 1.7e+308;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE = 9223372036854775816.43;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_SMALL_DOUBLE = -1.7e+308;
    final double EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE =
        -9223372036854775816.43;
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_INT_CONSTANT,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_INT_CONSTANT_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_INT_CONSTANT,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_INT_CONSTANT_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_DOUBLE,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_DOUBLE_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_SMALL_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALL_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_SMALL_DOUBLE,
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALL_DOUBLE_TEST));
    assertEquals(
        EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE,
        DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE_TEST,
        EPSILON,
        String.format(
            ASSERTION_MESSAGE_FOR_RENDERED_DOUBLE_CONSTANTS_TEST,
            EXPECTED_DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE,
            DoubleConstantsTestConstants
                .DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE_TEST));
    assertTrue(
        Double.class.isInstance(DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_INT_CONSTANT_TEST),
        String.format(ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_INT_CONSTANT_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_NEGATIVE_INT_CONSTANT_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_LARGEST_INT_CONSTANT_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_SMALLEST_INT_CONSTANT_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS,
            "DOUBLE_ASSIGNED_TO_DOUBLE_WITH_MANY_DECIMALS_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_FRACTIONAL_DOUBLE_TEST"));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS,
            "DOUBLE_ASSIGNED_TO_NEGATIVE_FRACTIONAL_DOUBLE_TEST"));
    // assertTrue(
    //    String.format(ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_LARGE_DOUBLE_TEST"),
    //
    // Double.class.isInstance(DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_DOUBLE_TEST));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_LARGE_FRACTIONAL_DOUBLE_TEST"));
    // assertTrue(
    //    String.format(ASSERTION_MESSAGE_FOR_TYPE_CHECKS, "DOUBLE_ASSIGNED_TO_SMALL_DOUBLE_TEST"),
    //
    // Double.class.isInstance(DoubleConstantsTestConstants.DOUBLE_ASSIGNED_TO_SMALL_DOUBLE_TEST));
    assertTrue(
        Double.class.isInstance(
            DoubleConstantsTestConstants
                .DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE_TEST),
        String.format(
            ASSERTION_MESSAGE_FOR_TYPE_CHECKS,
            "DOUBLE_ASSIGNED_TO_NEGATIVE_BUT_LARGE_FRACTIONAL_DOUBLE_TEST"));
  }
}
