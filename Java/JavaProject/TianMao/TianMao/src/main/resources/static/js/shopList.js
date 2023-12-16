$(function(){
		var nowImg = 1;
		var moveLeft = {
			'left':'0px',
			'transition':'all 0.5s'
		}
		var moveRight = {
			'left':'-840px',
			'transition':'all 0.5s'
		}
		imgAutoMove();

		//banner点击切换
		$("#one").click(function(){moveToLeft();});
		$("#tow").click(function(){moveToRight();});
		$(".tabBut").click(function(){
			if(nowImg==1)
				moveToLeft();
			else
				moveToRight();
		})
		// 定义计时器
		function autoRun(){
		    return setInterval(imgAutoMove,1500);
		}
		var timer=autoRun(); //调用定时器；
		//定时器    自动轮播图片
		$('.sec-item').hover(function(){
		    clearInterval(timer);
		},function(){
		    timer=autoRun();
		})
		function imgAutoMove(){
			if(nowImg==1)
				moveToLeft();
			else
				moveToRight();
		}
		function moveToLeft(){
			$("#one").css("background","#3167CE");
			$("#tow").css("background","black");
			$(".nav-tab-img").css(moveLeft);
			nowImg=2;
		}
		function moveToRight(){
			$("#tow").css("background","#3167CE");
			$("#one").css("background","black");
			$(".nav-tab-img").css(moveRight);
			nowImg=1;
		}




		// 全部和新奇酷玩 点击切换
		$("#cool-Item-But").click(function(){
			$(".allItem").fadeOut();
			$("#all-Item-But").css({"border-bottom":"3px solid #fff","color":"#333"});
			$(this).css({"border-bottom":"3px solid #06bda7","color":"#06bda7"});
			$(".coolItem").fadeIn();
		})
		$("#all-Item-But").click(function(){
			$(".coolItem").fadeOut();
			$("#cool-Item-But").css({"border-bottom":"3px solid #fff","color":"#333"});
			$(this).css({"border-bottom":"3px solid #06bda7","color":"#06bda7"});
			$(".allItem").fadeIn();
		})




		// 购物车动画
		var offset = $("#end").offset();
		$(".addCart").click(function(event){
			var addCart = $(this);
			var img = addCart.parent().find('.shopImg').attr('src');	//获取当前图片点击链接
			var flyer = $('<img class="u-flyer" src="'+img+'">');		//fly对象

			console.log(event.pageX+event.pageY);
			flyer.fly({
				start: {
					left:event.pageX,
					top:150
				},
				end: {
					left: 1680,
					top: 260,
					width: 0,
					height: 0
				},
				onEnd: function(){
					$("#msg").show().animate({width: '200px'}, 200).fadeOut(500);
					addCart.css({"background":"red","cursor":"default"}).unbind('click');
					addCart.find('span').text('已经添加进购物车');
					this.destory();
				}
			});
		});



// 跳转 商品展示
	$(".shopImg").click(function(){
		
		var nowImg = $(this).attr("src");
		var title = $(this).parent().find('.text h1').text();
		var info = $(this).parent().find('.text p').text();
		var price = $(this).parent().find('.price b').text();
		console.log(nowImg+"```"+title+"````"+info);
		window.location="showShop.html?imgPath="+nowImg+"&title="+title+"&info="+info+"&price="+price
	})



})

