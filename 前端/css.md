# CSS基础语法

```html
<body>
<div class="box">
  <h1>css基础内容</h1>
  <p>
    CSS是层叠样式表的缩写，用于控制网页的样式和布局。它可以控制文本、颜色、背景、边框、间距、字体、大小、位置等。使用CSS可以让网页更美观、易读、易维护。掌握CSS基础内容对于网页设计和开发非常重要。
  </p>
  <span>努力学习</span>
</div>
</body>
```

## 导入css

在主页中导入css需要使用`<link>`标签：

`<link rel="..." href="./css/index.css">`

## 基础语法

```css
* {
  /* 清除浏览器自带的默认边距样式 */
  margin: 0;
  padding: 0;
}

.box {
  /* 1. 尺寸 */
  /* 宽度与高度，默认值为auto */
  width: 100px;
  height: 100px;
  /* 边框：宽度、样式、颜色, 圆角效果：半径 */
  border: 3px dotted grey;
  border-radius: 8px;
  /* 内边距：边框与内容之间的边距，可以单值也可以双值 */
  padding: 15px 20px;
  /* 如果宽度或者高度使用百分比，元素就会超出页面，因为加上了内边距 */
  width: 100%;
  /* 可以使用box-sizing调节样式出现效果，不会超出页面，非常有用 */
  box-sizing: border-box;
  /* 外边距，与外部节点之间的距离*/
  margin: 30px;
  margin-bottom: 50px;
  /* 可以使用居中显示，auto代表自适应  */
  margin-left: auto;
  margin-right: auto;

  /* 2. 背景处理 */
  background-color: #f9f9f9;
  background-image: url(./image.png);
  /* 阴影效果：水平偏移、垂直偏移、色晕大小、颜色 */
  box-shadow: 1px 1px 1px gray;

  /* 启用相对定位，对元素本身没什么效果，影响子集 */
  position: relative;
}

/* 3. 文本处理 */
.box p {
  color: red;
  /* 首行缩进，em表示当前文字的大小，2em表示缩进两字符 */
  text-indent: 2em;
}

.box h1 {
  font-size: 50px;
  font-family: "微软雅黑";
  /* 字体粗细，normal是默认的意思，值为400 */
  font-weight: normal;
  /* 设置文本水平居中显示 */
  text-align: center;
  /* 设置文本垂直居中，一般使用line-height与height配合 */
  height: 80px;
  line-height: 80px;
  /* 文字的阴影 */
  text-shadow: 5px 5px 5px gray;
  /* 文字下划线 */
  text-decoration: dashed underline red;
}

.box span {
  color: orange;
  /* 显示规则，vertical:垂直 l:left r:right */
  writing-mode: vertical-lr;
  /* 英文垂直显示 */
  /* text-orientation: upright; */

  /* 4. 位置 */
  /* 绝对定位，需要依赖其他标签，默认参考body标签 */
  position: absolute;
  top: 20px;
  left: 5px;
  /* 如果希望参考上级标签，需要在上级中添加relative属性 */
  /* fixed：基于当前的可视区域定位 */
  position: fixed;
}
```

# 布局处理

```html
<body>
<div class="flex-container">
  <div class="flex-item">
    <h1>1. css布局</h1>
    <p>这里使用的是flex布局</p>
  </div>
  <div class="flex-item">
    <h1>2. css布局</h1>
    <p>这里使用的是flex布局</p>
  </div>
  <div class="flex-item">
    <h1>3. css布局</h1>
    <p>这里使用的是flex布局</p>
  </div>
</div>
</body>
```

## flex

```css
* {
  margin: 0;
  padding: 0;
}

.flex-container {
  width: 100%;
  max-width: 800px;
  min-width: 500px;
  margin: 0 auto;
  /* 必须项，声明显示方式为flex */
  display: flex;
  /* 垂直方向上居中，默认值为flex-start：偏上 */
  align-items: center;
  /* 垂直方向位于底部 */
  align-items: flex-end;
  /* 水平方向上居中，默认值flex-start：偏左 */
  justify-content: center;
}

/* 选择 flex-item 的所有元素 */
.flex-item {
  padding: 10px;
  /* 设置元素的尺寸占比 */
  flex: 1;
}

/* 选择 flex-item 的第二个元素 */
.flex-item:nth-child(2) {
  /* 如果其他元素设置成1，那么就是：1:3:1 */
  flex: 3;
}

/* 选择 flex-item 的奇数位元素 */
.flex-item:nth-child(2n + 1) {
  /* 如果设置了宽度，那么其他的元素就自适应了 */
  width: 200px;
}
```

## grid

```css
.grid-container {
  display: grid;
  /* 设置固定列宽 */
  grid-template-columns: 200px 300px 200px;
  /* 设置百分号占比列宽 */
  grid-template-columns: 20% 50% 30%;
  /* 设置比例占比列宽，fr就是占位标志 */
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-columns: repeat(3, 1fr);
  /* 设置中间自适应 */
  grid-template-columns: 300px auto 200px;
  /* 设置列高 */
  grid-template-rows: 1fr 2fr;
}
```

