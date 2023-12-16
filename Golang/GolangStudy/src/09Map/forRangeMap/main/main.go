package main

import "fmt"

func main() {
	heroes := map[string]string{
		"hero1": "Ironman",
		"hero2": "SpiderMan",
		"hero3": "CaptainAmerica",
	}

	for index, value := range heroes {
		fmt.Printf("\nindex=%v, value=%v", index, value)
	}
}
