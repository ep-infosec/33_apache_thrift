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

if(ANDROID)

    set(THRIFT_AAR outputs/aar/thrift-debug.aar outputs/aar/thrift-release.aar)
    add_custom_command(
        OUTPUT ${THRIFT_AAR}
        COMMAND ${GRADLE_EXECUTABLE}
            -p "${CMAKE_CURRENT_SOURCE_DIR}/android"
            "-PbuildDir=${CMAKE_CURRENT_BINARY_DIR}/android/build" assemble
    )
    add_custom_target(thrift_aar ALL DEPENDS ${THRIFT_AAR})

else()
    if(NOT JAVA_INSTALL_DIR)
        if(IS_ABSOLUTE "${LIB_INSTALL_DIR}")
            set(JAVA_INSTALL_DIR "${LIB_INSTALL_DIR}/java")
        else()
            set(JAVA_INSTALL_DIR "${CMAKE_INSTALL_PREFIX}/${LIB_INSTALL_DIR}/java")
        endif()
    endif()

    if(IS_ABSOLUTE "${DOC_INSTALL_DIR}")
        set(JAVA_DOC_INSTALL_DIR "${DOC_INSTALL_DIR}/java")
    else()
        set(JAVA_DOC_INSTALL_DIR "${CMAKE_INSTALL_PREFIX}/${DOC_INSTALL_DIR}/java")
    endif()

    set(PRELEASE "true")
    if (CMAKE_BUILD_TYPE MATCHES DEBUG)
        set(PRELEASE "false")
    endif ()

    file(GLOB_RECURSE THRIFTJAVA_SOURCES LIST_DIRECTORIES false
        "${CMAKE_CURRENT_SOURCE_DIR}/src/*")
    add_custom_command(OUTPUT "${CMAKE_CURRENT_BINARY_DIR}/build/libs/libthrift.jar"
        COMMENT "Building Java library using Gradle"
        COMMAND ${GRADLE_EXECUTABLE} ${GRADLE_OPTS} assemble
            --console=plain --no-daemon
            -Prelease=${PRELEASE}
            -Pthrift.version=${thrift_VERSION}
            "-Pbuild.dir=${CMAKE_CURRENT_BINARY_DIR}/build"
        DEPENDS ${THRIFTJAVA_SOURCES}
        WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
    )
    add_custom_target(ThriftJava ALL
        DEPENDS "${CMAKE_CURRENT_BINARY_DIR}/build/libs/libthrift.jar")

    # Enable publishing from CMake if the publishing information is provided
    add_custom_target(MavenPublish
        COMMENT "Publishing Java Library to Apache Maven staging"
        DEPENDS ThriftJava
        COMMAND ${GRADLE_EXECUTABLE} ${GRADLE_OPTS} clean uploadArchives
            --console=plain --no-daemon
            -Prelease=${PRELEASE}
            -Pthrift.version=${thrift_VERSION}
            "-Pbuild.dir=${CMAKE_CURRENT_BINARY_DIR}/build"
        WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
    )

    # Hook the CMake install process to the results from make ALL.
    # This works best when 'make all && sudo make install/fast' is used.
    # Using slash to end the source location to avoid copying the directory path.
    install(DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/build/libs/
            DESTINATION ${JAVA_INSTALL_DIR}
            FILES_MATCHING PATTERN "libthrift-${thrift_VERSION}.jar")
    install(DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/build/deps/
            DESTINATION ${JAVA_INSTALL_DIR}
            FILES_MATCHING PATTERN "*.jar")
    install(DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/build/docs/javadoc/
            DESTINATION ${JAVA_DOC_INSTALL_DIR})

    if(BUILD_TESTING)
        add_test(NAME JavaTest
            COMMAND ${GRADLE_EXECUTABLE} ${GRADLE_OPTS} test
                --console=plain --no-daemon
                -Prelease=${PRELEASE}
                -Pthrift.version=${thrift_VERSION}
                "-Pbuild.dir=${CMAKE_CURRENT_BINARY_DIR}/build"
                "-Pthrift.compiler=${THRIFT_COMPILER}"
            WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR})
    endif()

endif()
