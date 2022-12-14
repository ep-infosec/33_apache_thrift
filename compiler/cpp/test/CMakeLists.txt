#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements. See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied. See the License for the
# specific language governing permissions and limitations
# under the License.
#

file(GLOB KEYWORD_SAMPLES "${CMAKE_CURRENT_SOURCE_DIR}/keyword-samples/*.thrift")
set(KEYWORD_LANGS php)  # the whole ticket THRIFT-4655 is only about PHP so leave other languages alone
LIST(REMOVE_ITEM KEYWORD_LANGS swift) # in Swift you can escape reserved words (and in other languages as well)
foreach(LANG ${KEYWORD_LANGS})
    foreach(SAMPLE ${KEYWORD_SAMPLES})
        get_filename_component(FILENAME ${SAMPLE} NAME_WE)
        add_test(NAME "${LANG}_${FILENAME}"
            COMMAND thrift-compiler --gen ${LANG} ${SAMPLE})
        set_tests_properties("${LANG}_${FILENAME}" PROPERTIES
            PASS_REGULAR_EXPRESSION "Cannot use reserved language keyword")
    endforeach()
endforeach()

find_package(PythonInterp QUIET)
if(PYTHONINTERP_FOUND)
  add_test(NAME StalenessCheckTest COMMAND ${PYTHON_EXECUTABLE} ${CMAKE_CURRENT_SOURCE_DIR}/compiler/staleness_check.py ${THRIFT_COMPILER})
else()
  message(WARNING "Skipping StalenessCheckTest as there is no python interpreter available.")
endif()
