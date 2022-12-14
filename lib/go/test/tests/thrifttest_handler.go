/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * 'License'); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package tests

import (
	"context"
	"errors"
	"time"

	"github.com/apache/thrift/lib/go/test/gopath/src/thrifttest"
	"github.com/apache/thrift/lib/go/thrift"
)

type SecondServiceHandler struct {
}

func NewSecondServiceHandler() *SecondServiceHandler {
	return &SecondServiceHandler{}
}

func (p *SecondServiceHandler) BlahBlah(ctx context.Context) (err error) {
	return nil
}

func (p *SecondServiceHandler) SecondtestString(ctx context.Context, thing string) (r string, err error) {
	return thing, nil
}

type ThriftTestHandler struct {
}

func NewThriftTestHandler() *ThriftTestHandler {
	return &ThriftTestHandler{}
}

func (p *ThriftTestHandler) TestVoid(ctx context.Context) (err error) {
	return nil
}

func (p *ThriftTestHandler) TestString(ctx context.Context, thing string) (r string, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestBool(ctx context.Context, thing bool) (r bool, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestByte(ctx context.Context, thing int8) (r int8, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestI32(ctx context.Context, thing int32) (r int32, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestI64(ctx context.Context, thing int64) (r int64, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestDouble(ctx context.Context, thing float64) (r float64, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestBinary(ctx context.Context, thing []byte) (r []byte, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestUuid(ctx context.Context, thing thrift.Tuuid) (r thrift.Tuuid, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestStruct(ctx context.Context, thing *thrifttest.Xtruct) (r *thrifttest.Xtruct, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestNest(ctx context.Context, thing *thrifttest.Xtruct2) (r *thrifttest.Xtruct2, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestMap(ctx context.Context, thing map[int32]int32) (r map[int32]int32, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestStringMap(ctx context.Context, thing map[string]string) (r map[string]string, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestSet(ctx context.Context, thing []int32) (r []int32, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestList(ctx context.Context, thing []int32) (r []int32, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestEnum(ctx context.Context, thing thrifttest.Numberz) (r thrifttest.Numberz, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestTypedef(ctx context.Context, thing thrifttest.UserId) (r thrifttest.UserId, err error) {
	return thing, nil
}

func (p *ThriftTestHandler) TestMapMap(ctx context.Context, hello int32) (r map[int32]map[int32]int32, err error) {
	r = make(map[int32]map[int32]int32)
	pos := make(map[int32]int32)
	neg := make(map[int32]int32)

	for i := int32(1); i < 5; i++ {
		pos[i] = i
		neg[-i] = -i
	}
	r[4] = pos
	r[-4] = neg

	return r, nil
}

func (p *ThriftTestHandler) TestInsanity(ctx context.Context, argument *thrifttest.Insanity) (r map[thrifttest.UserId]map[thrifttest.Numberz]*thrifttest.Insanity, err error) {
	hello := thrifttest.NewXtruct()
	hello.StringThing = "Hello2"
	hello.ByteThing = 2
	hello.I32Thing = 2
	hello.I64Thing = 2

	goodbye := thrifttest.NewXtruct()
	goodbye.StringThing = "Goodbye4"
	goodbye.ByteThing = 4
	goodbye.I32Thing = 4
	goodbye.I64Thing = 4

	crazy := thrifttest.NewInsanity()
	crazy.UserMap = make(map[thrifttest.Numberz]thrifttest.UserId)
	crazy.UserMap[thrifttest.Numberz_EIGHT] = 8
	crazy.UserMap[thrifttest.Numberz_FIVE] = 5
	crazy.Xtructs = []*thrifttest.Xtruct{goodbye, hello}

	first_map := make(map[thrifttest.Numberz]*thrifttest.Insanity)
	second_map := make(map[thrifttest.Numberz]*thrifttest.Insanity)

	first_map[thrifttest.Numberz_TWO] = crazy
	first_map[thrifttest.Numberz_THREE] = crazy

	looney := thrifttest.NewInsanity()
	second_map[thrifttest.Numberz_SIX] = looney

	var insane = make(map[thrifttest.UserId]map[thrifttest.Numberz]*thrifttest.Insanity)
	insane[1] = first_map
	insane[2] = second_map

	return insane, nil
}

func (p *ThriftTestHandler) TestMulti(ctx context.Context, arg0 int8, arg1 int32, arg2 int64, arg3 map[int16]string, arg4 thrifttest.Numberz, arg5 thrifttest.UserId) (r *thrifttest.Xtruct, err error) {
	r = thrifttest.NewXtruct()
	r.StringThing = "Hello2"
	r.ByteThing = arg0
	r.I32Thing = arg1
	r.I64Thing = arg2
	return r, nil
}

func (p *ThriftTestHandler) TestException(ctx context.Context, arg string) (err error) {
	if arg == "Xception" {
		x := thrifttest.NewXception()
		x.ErrorCode = 1001
		x.Message = arg
		return x
	} else if arg == "TException" {
		return thrift.WrapTException(errors.New(arg))
	} else {
		return nil
	}
}

func (p *ThriftTestHandler) TestMultiException(ctx context.Context, arg0 string, arg1 string) (r *thrifttest.Xtruct, err error) {
	if arg0 == "Xception" {
		x := thrifttest.NewXception()
		x.ErrorCode = 1001
		x.Message = "This is an Xception"
		return nil, x
	} else if arg0 == "Xception2" {
		x2 := thrifttest.NewXception2()
		x2.ErrorCode = 2002
		x2.StructThing = thrifttest.NewXtruct()
		x2.StructThing.StringThing = "This is an Xception2"
		return nil, x2
	}

	res := thrifttest.NewXtruct()
	res.StringThing = arg1
	return res, nil
}

func (p *ThriftTestHandler) TestOneway(ctx context.Context, secondsToSleep int32) (err error) {
	time.Sleep(time.Second * time.Duration(secondsToSleep))
	return nil
}
