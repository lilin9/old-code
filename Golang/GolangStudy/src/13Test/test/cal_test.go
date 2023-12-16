package main

import (
	_ "fmt"
	"testing"
)

func TestAddUpper(t *testing.T) {
	res := addUpper(10)
	if res != 55 {
		//fmt.Printf("AddUpper(10) 执行错误，期望值为：%v，实际值为：%v\n", 55, res)
		t.Fatalf("AddUpper(10) 执行错误，期望值：%v，实际值：%v\n", 55, res)
	}

	t.Logf("AddUpper(10) 执行正确")
}
