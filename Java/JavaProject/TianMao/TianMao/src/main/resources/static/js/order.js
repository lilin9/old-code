function $(Nid){
 return document.getElementById(Nid);
}
window.onload = init;
function init (){
productCount();

}

//更新总金额
function productCount(){

	let price = document.getElementsByName('goodsprice');
	let num = document.getElementsByName('goodsnum');
	let sum = document.getElementsByName('goodssum');
	let allmoney = 0;
	for(let i = 0;i<num.length;i++){
		sum[i].innerHTML = price[i].innerHTML*num[i].value;
		allmoney+=Number(sum[i].innerHTML);
	}
	$("allmoney").innerHTML = allmoney;
}

