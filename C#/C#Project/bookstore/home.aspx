<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="home.aspx.cs" Inherits="bookstore.home"%>

<asp:Content ID="home" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <div class="section1">
		<section>
			<h1>Time of new life</h1>
			<p>新时代，唯书不可辜负</p>
            <p>年轻的人们让我们一起</p>
			<p>通过阅读，体验生活，享受生活</p>
			<a href="books.aspx?classId=0">进入书城</a>
		</section>
	</div>
	<div class="section2">
		<ul>
			<li><img src="images/section2_1.png"><p>悬疑丛书打造全新世界观<br>让你更爱你的生活</p></li>
			<li><img src="images/section2_2.png"><p>丰富多彩的游记文学<br>发挥世界的主人公意识</p></li>
			<li><img src="images/section2_3.png"><p>时尚的新概念书籍<br>超前体验未知的生活</p></li>
			<li><img src="images/section2_4.png"><p>完善的教辅材料<br>助力学习事半功倍</p></li>
		</ul>
	</div>
	<div class="section3">
		<img src="images/section3_logo_.png">
		<h1>关于新世界，你不知道的还有什么？</h1>
	</div>
	<div class="section4">
		<section style="background: rgba(159, 159, 159, 0.44);padding: 8px;border-radius: 5px;">
			<h2>新世界书城开设实体门店啦</h2><hr>
			<h4>每个一线城市都有相应的实体门店，请自主查询您所需要了解的门店信息</h4>
		</section>
	</div>
	<div class="section5">
		<ul>
			<li><img src="images/section5_1.png"><h4>北京实体店</h4><p>无声阅读活动</p></li>
			<li><h4>上海实体店</h4><p>知识海洋探索</p><img src="images/section5_2.png"></li>
			<li><img src="images/section5_3.png"><h4>深圳实体店</h4><p>亲子阅读活动</p></li>
			<li><img src="images/section5_4.png"><h4>广州实体店</h4><p>免费教辅大派送</p></li>
		</ul>
	</div>
	<div class="section6">
		<div class="left">
			<div class="content">
				<h2>新世界书城</h2>
				<h1>Bookstore</h1>
				<h4>给你想要的书籍</h4>
				<h4>阅读，使心灵宁静。</h4>
			</div>
		</div>
		<div class="right">
			<section>
				<h2>新世界/<span>bookstore</span></h2>
				<p>网站式购书平台。提供高质量，更快捷，更方便的购书方式。网上书城不仅可用于图书的在线销售、下载，也有音碟、影碟的在线销售，版权交易、图书馆的采购、团购等功能的实现，你可以在线分享你喜欢的图书、音像、图书资讯等给你的好友。而且网站式的书城对图书的管理更加合理化，信息化。售书的同时还具有书籍类商品管理、购物车、订单管理、会员管理等功能，非常灵活的网站内容和文章管理功能。</p>
				<a href="books.aspx?classId=0">进入书城</a>
			</section>
		</div>
	</div>
	<div class="section7">
		<div class="first">
			<section>
				<h3>新世界</h3>
				<h3>关于爱阅读的我们</h3>
				<hr>
				<a href="register.aspx">注册账号</a>
			</section>
		</div>
		<div class="second">
			<section>
				<h3>新世界</h3>
				<h3>关于爱生活的我们</h3>
				<hr>
				<a href="login.aspx">快速登录</a>
			</section>
		</div>
		<div class="three"></div>
	</div>
</asp:Content>

