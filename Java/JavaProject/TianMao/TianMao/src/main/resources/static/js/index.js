

$(function(){
	var imgsPath=['images/banner-1.jpg','images/banner-2.jpg','images/banner-3.jpg','images/banner-4.jpg',"images/banner-5.jpg"];
	var imgsColor=['#4A0296','#9A0CF4','#E8E8E8','#F8651F','#E8E8E8'];
	var liArray=$(".banner-item ul li");
	var nowImgNum = 0;
	imgAutoMove();
		for(var i = 0;i<liArray.length;i++){
			liArray[i].path = imgsPath[i];
			liArray[i].color = imgsColor[i];
			liArray[i].num = i;
		}

		//banner     鼠标移上去 设置背景颜色、图片路径
		liArray.mouseover(function(){

			$('.banner').css("background-color",this.color);
			$('#BannerImg').attr("src",this.path);


			$('#BannerImg').fadeIn(1000);
			nowImgNum = this.num;
			clearInterval(timer);
			$(liArray).css("background","rgba(0, 0, 0, 0.2)");
			$(this).css('background','#fff');
		})



var liOneArray = $(".li-One");
	liOneArray.mouseover(function(){
		$(this).children(".Sec-nav-box").css("display","block")
		var arry = $(this).children(".Sec-nav-box");
	})
	liOneArray.mouseout(function(){
		$(this).children(".Sec-nav-box").css("display","none")
	})

// function ajax(){

// }

// $.ajax({
// 	url:"",
// 	data:{username:"zhangsan"},
// 	type:"get",
// 	dataType:"json",

//    success:function(data){
// window.location="data.url"
//    },
//    error:function(){

//    }

// })


		//定义计时器
		function autoRun(){
		    return setInterval(imgAutoMove,1500);
		}
		var timer=autoRun(); //调用定时器；
		//定时器    自动轮播图片
		$('#BannerImg').hover(function(){
		    clearInterval(timer);
		},function(){

		    timer=autoRun();
		})
		//轮播函数
		function imgAutoMove(){
			if(nowImgNum>4)nowImgNum=0;

			$('.banner').css("background-color",imgsColor[nowImgNum]);
			$('#BannerImg').attr("src",imgsPath[nowImgNum]);

			$(liArray).css("background","rgba(0, 0, 0, 0.2)");
			$(liArray[nowImgNum]).css("background","#fff");
			nowImgNum++;
		}



})
