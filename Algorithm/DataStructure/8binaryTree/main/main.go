package main

import "fmt"

type HeroTree struct {
	Id    int       //排名
	Name  string    //名字
	Left  *HeroTree //左指针
	Right *HeroTree //右指针
}

//构建 二叉树
func (this *HeroTree) getTree(root *HeroTree) {
	captain := &HeroTree{
		Id:   1,
		Name: "captain",
	}
	ironman := &HeroTree{
		Id:   2,
		Name: "ironMan",
	}

	root.Right = ironman
	root.Left = captain

	spiderMan := &HeroTree{
		Id:   3,
		Name: "spiderMan",
	}
	buckie := &HeroTree{
		Id:   4,
		Name: "buckie",
	}
	hawkeye := &HeroTree{
		Id:   5,
		Name: "hawkeye",
	}

	ironman.Right = spiderMan
	captain.Left = buckie
	captain.Right = hawkeye
}

//前序遍历二叉树：先输出 root 节点，再输出左子树，然后输出右子树
func (this *HeroTree) preOrder(node *HeroTree) {
	if node != nil {
		fmt.Printf("排名: %d  称号: %s\n", node.Id, node.Name)
		//输出左子树
		node.preOrder(node.Left)
		//输出右子树
		node.preOrder(node.Right)
	}
}

//中序遍历二叉树：先输出左子树，再输出 root 节点，然后输出右子树
func (this *HeroTree) infixOrder(node *HeroTree) {
	if node != nil {
		//输出左子树
		node.infixOrder(node.Left)
		fmt.Printf("排名: %d  称号: %s\n", node.Id, node.Name)
		//输出右子树
		node.infixOrder(node.Right)
	}
}

//后序遍历二叉树：先输出左子树，再输出右子树，然后输出 root 节点
func (this *HeroTree) postOrder(node *HeroTree) {
	if node != nil {
		//输出左子树
		node.postOrder(node.Left)
		//输出右子树
		node.postOrder(node.Right)
		fmt.Printf("排名: %d  称号: %s\n", node.Id, node.Name)
	}
}

func main() {
	tree := &HeroTree{}
	root := &HeroTree{
		Id:   0,
		Name: "avenger",
	}

	//构建二叉树
	tree.getTree(root)
	fmt.Println("前序遍历二叉树:")
	//前序遍历二叉树
	tree.preOrder(root)

	fmt.Println("\n中序遍历二叉树:")
	//中序遍历二叉树
	tree.infixOrder(root)

	fmt.Println("\n后序遍历二叉树:")
	//后序遍历二叉树
	tree.postOrder(root)
}
