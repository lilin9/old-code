//按钮的显示和隐藏
function showAndHide() {
    $(".You-Likw-item").hover(function () {
        //当鼠标移入时，显示 button 按钮
        $(this).children("button").show();
    }, function () {
        //当鼠标移出时，隐藏 button 按钮
        $(this).children("button").hide()
    })

}

//为加入购物车添加点击事件
function addToCartButton() {
    $(".addToCart").click(function() {
        location.href = "showShop.html";
    });
}

//为删除按钮绑定点击事件
function deleteCollectionButton() {
    $(".deleteGoods").click(function () {
        if (!confirm("确定要删除这个收藏夹吗？")) return;
        deleteCollection();
        $(this).parent("div").css("display", "none");
    });
}