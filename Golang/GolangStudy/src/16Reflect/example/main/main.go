package main

import (
	"fmt"
	"reflect"
)

type Student struct {
	Name string
	Age  int
}

func ReflectTest(param interface{}) {
	//获取 reflect.Type
	reflectType := reflect.TypeOf(param)
	fmt.Printf("reflectType = %v, type = %T", reflectType, reflectType)

	//获取 reflect.Value
	reflectValue := reflect.ValueOf(param)
	fmt.Printf("\nreflectValue = %v, type = %T \n", reflectValue, reflectValue)

	//获取 Name 字段值
	reflectInterface := reflectValue.Interface()
	student, ok := reflectInterface.(Student)
	if ok {
		fmt.Println("name of student: ", student.Name)
	}
}

func putReflectForInt(params interface{}) {
	value := reflect.ValueOf(params)
	value.Elem().SetInt(20)

	fmt.Println("putReflect() -> ", value.Elem())
}

func putReflectForStruct(params interface{}) {
	value := reflect.ValueOf(params)
	fmt.Println(value.Elem())
}

func main() {
	stu := Student{"tom", 12}
	//num := 10
	//ReflectTest(stu)

	//putReflectForInt(&num)
	//fmt.Println("main() -> ", num)

	putReflectForStruct(&stu)
}
