package main

import "fmt"

func main() {
	//接收用户输入
	key := ""
	//控制循环输出
	loop := true

	//账户余额
	var banlance = float32(10000.0)
	//每次收支的金额
	var money = float32(0.0)
	//每次收支的说明
	note := ""
	//收支详情说明
	detail := "收支\t账户金额\t收支金额\t说明"
	//判断是否有余额
	flag := false

	//显示主菜单
	for {
		fmt.Println("---------- 家庭收支记账软件 ----------")
		fmt.Println("           1 收支明细")
		fmt.Println("           2 登记收入")
		fmt.Println("           3 登记支出")
		fmt.Println("           4 退出软件")
		fmt.Print("请选择（1-4）：")

		fmt.Scan(&key)

		switch key {
		case "1":
			fmt.Println("---------- 当前收支明细记录 ----------")
			if flag {
				fmt.Println(detail)
			} else {
				fmt.Println("目前还没有任何收入……")
			}
		case "2":
			fmt.Print("本次收入金额：")
			fmt.Scan(&money)
			banlance += money //修改账户余额

			fmt.Print("本次收入说明：")
			fmt.Scan(&note)
			//拼接收入详情
			detail += fmt.Sprintf("\n收入\t%v\t\t%v\t\t%v", banlance, money, note)
			flag = true
		case "3":
			fmt.Print("本次支出金额：")
			fmt.Scan(&money)
			if banlance < money {
				fmt.Println("余额不足……")
				break
			}
			banlance -= money //修改账户余额

			fmt.Print("本次支出说明：")
			fmt.Scan(&note)
			//拼接收入详情
			detail += fmt.Sprintf("\n支出\t%v\t\t%v\t\t%v", banlance, money, note)
			flag = true
		case "4":
			fmt.Print("是否选择退出（y/n）：")
			choose := ""
			for {
				fmt.Scan(&choose)
				if choose == "y" || choose == "n" {
					break
				}
				fmt.Print("输入有误，请重新输入（y/n）：")
			}
			if choose == "y" {
				loop = false
			}
		default:
			fmt.Println("请输入正确选项……")
		}

		//退出循环
		if !loop {
			break
		}
	}
	fmt.Println("你退出了家庭收支记账软件的使用……")
}
