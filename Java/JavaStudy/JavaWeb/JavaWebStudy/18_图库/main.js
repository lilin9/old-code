const displayedImage = document.querySelector('.displayed-img');
const thumbBar = document.querySelector('.thumb-bar');

const btn = document.querySelector('button');
const overlay = document.querySelector('.overlay');


/* 添加图片循环 */
for (let i=1; i<=5; i++) {
	const newImage = document.createElement('img');
	newImage.setAttribute('src', ('images/pic' + i + '.jpg'));
	thumbBar.appendChild(newImage);

	newImage.onclick = function(e) {
		displayedImage.src = e.target.src;
		/e.target.src: 指向按钮本身，即取得每张略缩图的 src 赋值给displayImage变量的 src 属性/
	};
}


/* 编写 变暗/变量 按钮功能 */
btn.onclick = function() {
	if (btn.getAttribute("class") === "dark") {
		btn.setAttribute('class', "light");
		btn.textContent = "变亮";
		overlay.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
	}
	else {
		btn.setAttribute('class', "dark");
		btn.textContent = "变暗";
		overlay.style.backgroundColor = "rgba(0, 0, 0, 0)";
	}
}
