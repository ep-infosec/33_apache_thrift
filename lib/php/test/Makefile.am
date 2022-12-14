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

PHPUNIT=php $(top_srcdir)/vendor/bin/phpunit

stubs: ../../../test/v0.16/ThriftTest.thrift  TestValidators.thrift
	mkdir -p ./packages/php
	$(THRIFT) --gen php -r --out ./packages/php ../../../test/v0.16/ThriftTest.thrift
	mkdir -p ./packages/phpv
	mkdir -p ./packages/phpvo
	mkdir -p ./packages/phpjs
	$(THRIFT) --gen php:validate     -r --out ./packages/phpv   TestValidators.thrift
	$(THRIFT) --gen php:validate,oop -r --out ./packages/phpvo  TestValidators.thrift
	$(THRIFT) --gen php:json         -r --out ./packages/phpjs  TestValidators.thrift

deps: $(top_srcdir)/composer.json
	composer install --working-dir=$(top_srcdir)

all-local: deps

check-json-serializer: deps stubs
	$(PHPUNIT) --log-junit=TEST-log-json-serializer.xml JsonSerialize/

check-validator: deps stubs
	$(PHPUNIT) --log-junit=TEST-log-validator.xml Validator/

check-protocol:	deps stubs
	$(PHPUNIT) --log-junit=TEST-log-protocol.xml Protocol/

check: deps stubs \
  check-protocol \
  check-validator \
  check-json-serializer

distclean-local:

clean-local:
	$(RM) -r ./packages
	$(RM) TEST-*.xml
