// 设置画布

const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

const width = canvas.width = window.innerWidth;
const height = canvas.height = window.innerHeight;

// 生成随机整数的函数

function random(min,max) {
  const num = Math.floor(Math.random() * (max - min)) + min;
  return num;
}

//生成随机的颜色值
function randomColor() {
  return "rgb(" +
          random(0,255) + ", " +
          random(0,255) + ", " +
          random(0,255) + ")";
}

function Ball(x, y, velX, velY, color, size) {
  this.x = x;
  this.y = y;
  this.velX = velX; //水平速度
  this.velY = velY; //竖直速度
  this.color = color;
  this.size =size;
}

//绘制小球
Ball.prototype.draw = function() {
  ctx.beginPath();  //声明开始在屏幕上绘制图形
  ctx.fillStyle = this.color;  //通过fillStyle定义图形的颜色
  ctx.arc(this.x, this.y, this.size, 0, 2*Math.PI);  //使用arc()在纸上画出圆弧
  ctx.fill();  //声明结束在屏幕上绘制图形
}

//以下代码使小球开始移动
//规定小球的移动范围
Ball.prototype.update = function() {
  if ((this.x + this.size) >= width) {
    this.velX = -(this.velX);
  }

  if ((this.x - this.size) <= 0) {
    this.velX = -(this.velX);
  }

  if ((this.y + this.size) >= height) {
    this.velY = -(this.velY);
  }

  if ((this.y - this.size) <= 0) {
    this.velY = -(this.velY);
  }

  this.x += this.velX;
  this.y += this.velY;
}

//碰撞检测
Ball.prototype.collisionDetect = function() {
  for (let i = 0; i < balls.length; i++) {
    //判断当前小球是不是同一个
    if (this !== balls[i]) {
      const dx = this.x - balls[i].x;
      const dy = this.y - balls[i].y;
      const distance = Math.sqrt(dx * dx + dy * dy);

      if (distance < this.size + balls[i].size) {
        balls[i].color = this.color = randomColor();
      }
    }
  }
}

let balls = [];

while (balls.length < 50) {
  let size = random(10, 20);
  let ball = new Ball(
      //为避免出现绘制错误，令球与画布边缘始终相距球本身一倍宽度的距离
      random(0 + size, width - size),
      random(0 + size, height - size),
      random(-7, 7),
      random(-7, 7),
      randomColor(),
      size
    );
  balls.push(ball);
}

/*几乎所有的动画效果都会用到一个运动循环，也就是每一帧都自动更新视图。
这是大多数游戏或者其他类似项目的基础*/
function loop() {
  ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
  ctx.fillRect(0, 0, width, height);

  for (let i = 0; i < balls.length; i++) {
    balls[i].draw();
    balls[i].update();
    balls[i].collisionDetect();
  }

  //调用requestAnimationFrame() 方法再运行一次函数，以此来得到一个平滑的动画效果
  //递归
  requestAnimationFrame(loop);
}
loop();