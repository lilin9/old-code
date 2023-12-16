// 设置画布

const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

const width = canvas.width = window.innerWidth;
const height = canvas.height = window.innerHeight;

let count = 0;
const paras = document.querySelector("p");

// 生成随机整数的函数

function random(min,max) {
  return Math.floor(Math.random() * (max - min)) + min;
}


//生成随机的颜色值
function randomColor() {
  return "rgb(" +
          random(0,255) + ", " +
          random(0,255) + ", " +
          random(0,255) + ")";
}


function Shape(x, y, velX, velY, exists) {
  this.x = x;
  this.y = y;
  this.velX = velX; //水平速度
  this.velY = velY; //竖直速度
  this.exists = exists; //标记球是否存在于程序中 (没有被恶魔圈吃掉)
}

//Ball继承于Shape
function Ball(x, y, velX, velY, exists, color, size) {
  //对象冒充，实现属性继承
  Shape.call(this, x, y, velX, velY, exists);
  this.color = color;
  this.size = size;
}
Ball.prototype = Object.create(Shape.prototype);
Ball.prototype.constructor = Ball;


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


//球 碰撞检测
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

 //恶魔圈
function EvilCircle(x, y, exists) {
  Shape.call(this, x, y, 20, 20, exists);
  this.color = "white";
  this.size = 10;
}
EvilCircle.prototype = Object.create(Shape.prototype);
EvilCircle.prototype.constructor = EvilCircle;

//绘制恶魔圈
EvilCircle.prototype.draw = function() {
  ctx.beginPath();
  ctx.lineWidth = 3;
  ctx.strokeStyle = this.color;
  ctx.arc(this.x, this.y, this.size, 0, 2*Math.PI);
  ctx.stroke();
}

//检测恶魔圈是否将要超出屏幕边界
EvilCircle.prototype.checkBounds = function() {
  if ((this.x + this.size) >= width) {
    //this.x = random(0, width);
    this.x -= this.size ;
  }

  if ((this.x - this.size) <= 0) {
    //this.x = random(0, width);
    this.x += this.size;
  }

  if ((this.y + this.size) >= height) {
    //this.y = random(0, height);
    this.y -= this.size;
  }

  if ((this.y - this.size) <= 0) {
    //this.y = random(0, height);
    this.y += this.size;
  }
}

//通过事件监听器(onkeydown)监听键盘按钮，从而移动恶魔圈
EvilCircle.prototype.setControls = function() {
  var _this = this;
  window.onkeydown = function(e) {
  switch(e.key) {
    case 'a':
      _this.x -= _this.velX;
      break;
    case 'd':
      _this.x += _this.velX;
      break;
    case 'w':
      _this.y -= _this.velY;
      break;
    case 's':
      _this.y += _this.velY;
      break;
    }
  }
};


//恶魔圈 碰撞检测
EvilCircle.prototype.collisionDetect = function() {
  for (let i = 0; i < balls.length; i++) {
    //检测小球是否与恶魔圈相撞
    if (balls[i].exists) {
      const dx = this.x - balls[i].x;
      const dy = this.y - balls[i].y;
      const distance = Math.sqrt(dx * dx + dy * dy);

      if (distance < this.size + balls[i].size) {
        balls[i].exists = false;
        count--;

        //绘制分数
        paras.replace("多少", count);
        //paras.textContent = "Ball count:" + count；
      }
    }
  }
}

let balls = [];
while (balls.length <= 25) {
  let size = random(10, 20);
  let ball = new Ball(
      //为避免出现绘制错误，令球与画布边缘始终相距球本身一倍宽度的距离
      random(0 + size, width - size),
      random(0 + size, height - size),
      random(-7, 7),
      random(-7, 7),
      true,
      randomColor(),
      size
    );
  balls.push(ball);
  count++;
  //paras.replace("多少", count);
}

/*几乎所有的动画效果都会用到一个运动循环，也就是每一帧都自动更新视图。
这是大多数游戏或者其他类似项目的基础*/
let evilCircle = new EvilCircle(random(0, width), random(0, height), true);
evilCircle.setControls();

//刷新
function loop() {
  ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
  ctx.fillRect(0, 0, width, height);

  for (let i = 0; i < balls.length; i++) {
    if (balls[i].exists) {
      balls[i].draw();
      balls[i].update();
      balls[i].collisionDetect();
    }
  }

  evilCircle.draw();
  evilCircle.checkBounds();
  evilCircle.collisionDetect();

  //调用requestAnimationFrame() 方法再运行一次函数，以此来得到一个平滑的动画效果
  //递归
  requestAnimationFrame(loop);
}
loop();