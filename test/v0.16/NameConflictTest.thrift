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

// Naming testcases, sepcifically for these tickets (but not limited to them)
// THRIFT-2508 Uncompileable C# code due to language keywords in IDL
// THRIFT-2557 error CS0542 member names cannot be the same as their enclosing type


struct using {
    1: double single
    2: double integer
}

struct delegate {
    1: string partial
    2: delegate delegate
}

struct get {
    1: bool sbyte
}

struct partial {
    1: using using
    2: bool read
    3: bool write
}

enum Maybe {
  JUST = 1,
  TRUE = 2,
  FALSE = 3
}

enum Either {
  LEFT = 1,
  RIGHT = 2
}

struct foldr {
  1: string id
}

struct of {
  1: string let
  2: string where
}

struct ofOf {
  1: of Of
}


struct ClassAndProp {
  1: bool ClassAndProp
  2: bool ClassAndProp_
  3: bool ClassAndProp__
  4: bool ClassAndProper
}

struct second_chance {
  1: bool SECOND_CHANCE
  2: bool SECOND_CHANCE_
  3: bool SECOND_CHANCE__
  4: bool SECOND_CHANCES
}

struct NOW_EAT_THIS {
  1: bool now_eat_this
  2: bool now_eat_this_
  3: bool now_eat_this__
  4: bool now_eat_this_and_this
}

struct TheEdgeCase {
  1: bool theEdgeCase
  2: bool theEdgeCase_
  3: bool theEdgeCase__
  4: bool TheEdgeCase
  5: bool TheEdgeCase_
  6: bool TheEdgeCase__
}

struct Tricky_ {
  1: bool tricky
  2: bool Tricky
}

struct Nested {
  1: ClassAndProp ClassAndProp
  2: second_chance second_chance
  3: NOW_EAT_THIS NOW_EAT_THIS
  4: TheEdgeCase TheEdgeCase
  5: Tricky_ Tricky_
  6: Nested Nested
}

exception Problem_ {
  1: bool problem
  2: bool Problem
}

struct Thrift5626 {
   1: i8       i8
   2: i16      i16
   3: i32      i32
   4: i64      i64
   //5: uuid     uuid
   6: string   string
   7: binary   binary
   8: bool     bool
   9: byte     byte
  10: list<string>        list
  11: set<string>         set
  12: map<string,string>  map
}

service extern {
    delegate event(1: partial get)
    void Foo(1: Nested Foo_args) throws (1: Problem_ Foo_result)
}

service qualified {
    Maybe maybe(1: Maybe foldr)
    Either either(1: foldr of)
}
// eof
