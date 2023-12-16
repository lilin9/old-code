//获取地址栏 得到图片
/*

$(function () {
    let str = decodeURI(window.location.href);
    let num = str.indexOf("?");
    if (num === -1) return;
    str = str.substr(num + 1); //取得所有参数   stringlet.substr(start [, length ]

    let arr = str.split("&"); //各个参数放到数组里
    console.log(arr)
    let imgPath = arr[0].substr(8);
    let title = arr[1].substr(6);
    let info = arr[2].substr(5);
    let price = arr[3].substr(6);
    console.log(imgPath);
    console.log(title);
    console.log(info);
    console.log(price);
    $("#middleImg").attr("src", imgPath);
    $("#bigImg").attr("src", imgPath);
    $("#smallImg").attr("src", imgPath);
    $(".showShop-content h1").text(title);
    $("#info").text(info);
    $(".nowPice span").text(price);

    // 更新
    let nowNum = $('#buyNum').val();
    let nowPrice = $(".nowPice").find('span').text();
    $('.oldPice s').text(nowPrice * 1 + 500);
    let SumPrice = nowNum * nowPrice;
    $('.changeNum p span').text(SumPrice);
    $('#huabei-item-one').find('b').text((SumPrice / 2.9).toFixed(1))
    $('#huabei-item-two').find('b').text((SumPrice / 5.5).toFixed(1))
    $('#huabei-item-three').find('b').text((SumPrice / 11).toFixed(1))

});
*/

// 放大镜
$(function () {
    $(".showImg-middle").mousemove(function (e) {
        $(".showImg-big").css({'display': 'block', 'z-index': '999'});
        $("#cover").css('display', 'block');
        let scrollTop = $(document).scrollTop();
        console.log(scrollTop);

        // 获得鼠标的绝对位置;
        let Mx = e.clientX;
        let My = e.clientY + scrollTop;

        //获得该控件的绝对位置
        let Ty = $('#middleImg').offset().top;
        let Tx = $('#middleImg').offset().left;


        // 图片上面小透明 的x-y
        let CoverX = Mx - Tx - 100;
        let CoverY = My - Ty - 100;


        // 判断当鼠标移出界
        if (CoverX <= 0) CoverX = 0;
        if (CoverY <= 0) CoverY = 0;
        if (CoverX >= 200) CoverX = 200;
        if (CoverY >= 200) CoverY = 200;

        $("#cover").css({'left': CoverX, 'top': CoverY});

        // 设置大图片的定位 (小图 200*200 原图800*800  相差4倍)
        let x = CoverX * 2;
        let y = CoverY * 2;
        $("#bigImg").css({"top": -y, "left": -x});
    })


    $(".showImg-middle").mouseout(function () {
        $(".showImg-big").css('display', 'none');
        $("#cover").css('display', 'none');
    })
})

//下面图片切换
$(function () {
    $('.imgList li').click(function () {
        $('.imgList li').css('border', '2px solid white')
        $(this).css('border', '2px solid #333')
        let nowCheckImgPath = $(this).find('img').attr('src');
        $("#middleImg").attr("src", nowCheckImgPath);
        $("#bigImg").attr("src", nowCheckImgPath);
    })
})

// 数字调整
$(function () {
    $('#add').click(function () {
        let nowNum = $('#buyNum').val()
        $('#buyNum').attr('value', nowNum * 1 + 1)
        update();
    })
    $('#minus').click(function () {
        let nowNum = $('#buyNum').val()
        if (nowNum == 1) return;
        $('#buyNum').attr('value', nowNum - 1)
        update();
    })

    function update() {
        let nowNum = $('#buyNum').val();
        let nowPrice = $(".nowPice").find('span').text();
        $('.oldPice s').text(nowPrice * 1 + 500);
        let SumPrice = nowNum * nowPrice;
        $('.changeNum p span').text(SumPrice);
        $('#huabei-item-one').find('b').text((SumPrice / 2.9).toFixed(1))
        $('#huabei-item-two').find('b').text((SumPrice / 5.5).toFixed(1))
        $('#huabei-item-three').find('b').text((SumPrice / 11).toFixed(1))
        // console.log(SumPrice)
    }
})