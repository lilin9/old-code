package main

import "fmt"

func main() {
	heroes := map[string]string{
		"hero1": "Ironman",
		"hero2": "SpiderMan",
	}

	val, isFind := heroes["hero3"]
	fmt.Printf("val=%v, isFind=%v", val, isFind)

}
