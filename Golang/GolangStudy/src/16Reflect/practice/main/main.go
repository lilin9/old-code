package main

import (
	"fmt"
	"reflect"
)

type Monster struct {
	Name  string  `json:"name"`
	Age   int     `json:"age"`
	Score float32 `json:"score"`
	Sex   string  `json:"sex"`
}

//Print 打印
func (monster Monster) Print() {
	fmt.Println("---------start---------")
	fmt.Println(monster)
	fmt.Println("----------end----------")
}

func (monster Monster) GetSum(n1, n2 int) int {
	return n1 + n2
}

//SetMonster monster的set方法
func (monster Monster) SetMonster(name string, age int, score float32, sex string) {
	monster.Name = name
	monster.Age = age
	monster.Score = score
	monster.Sex = sex
}

func testStruct(param interface{}) {
	//获取 type、value、kind
	typ := reflect.TypeOf(param)
	val := reflect.ValueOf(param)
	kind := val.Kind()

	//判断传入的参数类型是否是一个结构体
	if kind != reflect.Struct {
		fmt.Println("expect struct")
		return
	}

	//获取结构体的字段个数
	numField := val.NumField()
	fmt.Printf("结构体有 %d 个字段\n", numField)

	//遍历结构体所有字段
	for i := 0; i < numField; i++ {
		fmt.Printf("第 %d 个字段的值是 %v", i, val.Field(i))
		//获取到结构体的字段标签，需要通过 reflect.Type 来获取 tag 的值
		tagValue := typ.Field(i).Tag.Get("json")
		if tagValue != "" {
			fmt.Printf("\t这个字段的标签是 %v\n", tagValue)
		}
	}

	//获取结构体的方法个数
	numMethod := val.NumMethod()
	fmt.Printf("结构体有 %d 个自定义方法\n", numMethod)

	//调用结构体第二个方法
	//方法排序默认按照方法名的 ASCII 码大小来进行排序
	val.Method(1).Call(nil)

	//调用结构体第一个方法，计算两数之和
	var nums []reflect.Value
	nums = append(nums, reflect.ValueOf(10))
	nums = append(nums, reflect.ValueOf(20))
	result := val.Method(0).Call(nums)
	fmt.Printf("结构体第一个方法的返回值是 %d\n", result[0].Int())
}

func main() {
	var monster = Monster{
		Name:  "猫妖",
		Age:   500,
		Score: float32(100),
		Sex:   "女",
	}

	testStruct(monster)
}
