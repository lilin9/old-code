package main

import "fmt"

func cal(n1 float64, n2 float64, operator byte) float64 {
	var result float64
	switch operator {
	case '+':
		result = n1 + n2
	case '-':
		result = n1 - n2
	case '*':
		result = n1 * n2
	case '/':
		result = n1 / n2
	default:
		fmt.Println("error")
	}
	return result
}

func getSumAndSub(n1 int, n2 int) (int, int) {
	sum := n1 + n2
	sub := n1 - n2
	return sum, sub
}

func main() {
	fmt.Println(cal(1, 2, '+'))

	sum, sub := getSumAndSub(1, 2)
	fmt.Printf("sum = %v, sub = %v\n", sum, sub)

	sum2, _ := getSumAndSub(3, 4)
	fmt.Println("sum2 = ", sum2)
}
