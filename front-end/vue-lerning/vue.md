##  12. Vue

安装vue

```
# 最新稳定版
$ npm install vue
```

###   1、vue声明式渲染

```javascript

        let vm = new Vue({
            el: "#app",//绑定元素
            data: {  //封装数据
                name: "张三",
                num: 1
            },
            methods:{  //封装方法
                cancle(){
                    this.num -- ;
                },
                hello(){
                    return "1"
                }
            }
        });

```



### 2、双向绑定,模型变化，视图变化。反之亦然

双向绑定使用v-model

```html
 <input type="text" v-model="num">
```



```javascript
<h1> {{name}} ,非常帅，有{{num}}个人为他点赞{{hello()}}</h1>
```

### 3、事件处理

v-xx：指令
1、创建vue实例，关联页面的模板，将自己的数据（data）渲染到关联的模板，响应式的
2、指令来简化对dom的一些操作。
3、声明方法来做更复杂的操作。methods里面可以封装方法。



v-on是按钮的单击事件：

```
 <button v-on:click="num++">点赞</button>
```





在VUE中el,data和vue的作用:

- el：用来绑定数据；
- data:用来封装数据；
- methods：用来封装方法，并且能够封装多个方法，如何上面封装了cancell和hello方法。


为了方便的在浏览器上调试VUE程序，需要安装“[vue-devtools](https://github.com/vuejs/vue-devtools)”，编译后安装到chrome中即可。

详细的使用方法见：[Vue调试神器vue-devtools安装](https://www.jianshu.com/p/63f09651724c)



“v-html”不会对于HTML标签进行转义，而是直接在浏览器上显示data所设置的内容;而“ v-text”会对html标签进行转义

```javascript
     <div id="app">
        {{msg}}  {{1+1}}  {{hello()}}<br/>
        <span v-html="msg"></span>
        <br/>
        <span v-text="msg"></span>
    </div>
   
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        new Vue({
            el:"#app",
            data:{
                msg:"<h1>Hello</h1>",
                link:"http://www.baidu.com"
            },
            methods:{
                hello(){
                    return "World"
                }
            }
        })
    </script>
```



  {{msg}}  :称为差值表达式，它必须要写在Html表达式，可以完成数学运算和方法调用



### 4、v-bind :单向绑定

给html标签的属性绑定

```javascript
 <!-- 给html标签的属性绑定 -->
    <div id="app"> 

        <a v-bind:href="link">gogogo</a>

        <!-- class,style  {class名：加上？}-->
        <span v-bind:class="{active:isActive,'text-danger':hasError}"
          :style="{color: color1,fontSize: size}">你好</span>

    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        let vm = new Vue({
            el:"#app",
            data:{
                link: "http://www.baidu.com",
                isActive:true,
                hasError:true,
                color1:'red',
                size:'36px'
            }
        })
    </script>
```

上面所完成的任务就是给a标签绑定一个超链接。并且当“isActive”和“hasError”都是true的时候，将属性动态的绑定到，则绑定该“active”和 "text-danger"class。这样可以动态的调整属性的存在。

而且如果想要实现修改vm的"color1"和“size”， span元素的style也能够随之变化，则可以写作v-bind:style，也可以省略v-bind。

### 5、v-model双向绑定

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>


    <!-- 表单项，自定义组件 -->
    <div id="app">

        精通的语言：
            <input type="checkbox" v-model="language" value="Java"> java<br/>
            <input type="checkbox" v-model="language" value="PHP"> PHP<br/>
            <input type="checkbox" v-model="language" value="Python"> Python<br/>
        选中了 {{language.join(",")}}
    </div>
    
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        let vm = new Vue({
            el:"#app",
            data:{
                language: []
            }
        })
    </script>

</body>
</html>
```

上面完成的功能就是通过“v-model”为输入框绑定多个值，能够实现选中的值，在data的language也在不断的发生着变化，

如果在控制台上指定vm.language=["Java","PHP"]，则data值也会跟着变化。

通过“v-model”实现了页面发生了变化，则数据也发生变化，数据发生变化，则页面也发生变化，这样就实现了双向绑定。



数组的连接操作： 选中了 {{language.join(",")}}

### 6、v-on为按钮绑定事件

```javascript
        <!--事件中直接写js片段-->
        <button v-on:click="num++">点赞</button>
        <!--事件指定一个回调函数，必须是Vue实例中定义的函数-->
        <button @click="cancle">取消</button>
```

上面是为两个按钮绑定了单击事件，其中一个对于num进行自增，另外一个自减。

v-on:click也可以写作@click



事件的冒泡：

```html
        <!-- 事件修饰符 -->
        <div style="border: 1px solid red;padding: 20px;" v-on:click="hello">
            大div
            <div style="border: 1px solid blue;padding: 20px;" @click="hello">
                小div <br />
                <a href="http://www.baidu.com" @click.prevent="hello">去百度</a>
            </div>
        </div>
```

上面的这两个嵌套div中，如果点击了内层的div，则外层的div也会被触发；这种问题可以事件修饰符来完成：

```html
        <!-- 事件修饰符 -->
        <div style="border: 1px solid red;padding: 20px;" v-on:click.once="hello">
            大div
            <div style="border: 1px solid blue;padding: 20px;" @click.stop="hello">
                小div <br />
                <a href="http://www.baidu.com" @click.prevent.stop="hello">去百度</a>
                <!--这里禁止了超链接的点击跳转操作，并且只会触发当前对象的操作-->
            </div>
        </div>
```


### 7、v-for遍历循环

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>

    <div id="app">
        <ul>
            <li v-for="(user,index) in users" :key="user.name" v-if="user.gender == '女'">
                <!-- 1、显示user信息：v-for="item in items" -->
               当前索引：{{index}} ==> {{user.name}}  ==>   {{user.gender}} ==>{{user.age}} <br>
                <!-- 2、获取数组下标：v-for="(item,index) in items" -->
                <!-- 3、遍历对象：
                        v-for="value in object"
                        v-for="(value,key) in object"
                        v-for="(value,key,index) in object" 
                -->
                对象信息：
                <span v-for="(v,k,i) in user">{{k}}=={{v}}=={{i}}；</span>
                <!-- 4、遍历的时候都加上:key来区分不同数据，提高vue渲染效率 -->
            </li>

            
        </ul>

        <ul>
            <li v-for="(num,index) in nums" :key="index"></li>
        </ul>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>
    <script>         
        let app = new Vue({
            el: "#app",
            data: {
                users: [{ name: '柳岩', gender: '女', age: 21 },
                { name: '张三', gender: '男', age: 18 },
                { name: '范冰冰', gender: '女', age: 24 },
                { name: '刘亦菲', gender: '女', age: 18 },
                { name: '古力娜扎', gender: '女', age: 25 }],
                nums: [1,2,3,4,4]
            },
        })
    </script>
</body>

</html>
```

4、遍历的时候都加上:key来区分不同数据，提高vue渲染效率



### 过滤器

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <!-- 过滤器常用来处理文本格式化的操作。过滤器可以用在两个地方：双花括号插值和 v-bind 表达式 -->
    <div id="app">
        <ul>
            <li v-for="user in userList">
                {{user.id}} ==> {{user.name}} ==> {{user.gender == 1?"男":"女"}} ==>
                {{user.gender | genderFilter}} ==> {{user.gender | gFilter}}
                <!-- 这里的"|"表示的管道，将user.gender的值交给genderFilter -->
            </li>
        </ul>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        // 全局过滤器 
        Vue.filter("gFilter", function (val) {
            if (val == 1) {
                return "男~~~";
            } else {
                return "女~~~";
            }
        })

        let vm = new Vue({
            el: "#app",
            data: {
                userList: [
                    { id: 1, name: 'jacky', gender: 1 },
                    { id: 2, name: 'peter', gender: 0 }
                ]
            },
            filters: {
                //// filters 定义局部过滤器，只可以在当前vue实例中使用
                genderFilter(val) {
                    if (val == 1) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            }
        })
    </script>
</body>

</html>
```



### 组件化

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>

    <div id="app">
        <button v-on:click="count++">我被点击了 {{count}} 次</button>

        <counter></counter>
        <counter></counter>
        <counter></counter>
        <counter></counter>
        <counter></counter>
        <!-- 使用所定义的组件button-counter -->
        <button-counter></button-counter>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>


    <script>
        //1、全局声明注册一个组件
        Vue.component("counter", {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次</button>`,
            data() {
                return {
                    count: 1
                }
            }
        });

        //2、局部声明一个组件
        const buttonCounter = {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次~~~</button>`,
            data() {
                return {
                    count: 1
                }
            }
        };

        new Vue({
            el: "#app",
            data: {
                count: 1
            },
            components: {
                //声明所定义的局部组件
                'button-counter': buttonCounter
            }
        })
    </script>
</body>

</html>
```



### 生命周期钩子函数

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <span id="num">{{num}}</span>
        <button @click="num++">赞！</button>
        <h2>{{name}}，有{{num}}个人点赞</h2>
    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>
    
    <script>
        let app = new Vue({
            el: "#app",
            data: {
                name: "张三",
                num: 100
            },
            methods: {
                show() {
                    return this.name;
                },
                add() {
                    this.num++;
                }
            },
            beforeCreate() {
                console.log("=========beforeCreate=============");
                console.log("数据模型未加载：" + this.name, this.num);
                console.log("方法未加载：" + this.show());
                console.log("html模板未加载：" + document.getElementById("num"));
            },
            created: function () {
                console.log("=========created=============");
                console.log("数据模型已加载：" + this.name, this.num);
                console.log("方法已加载：" + this.show());
                console.log("html模板已加载：" + document.getElementById("num"));
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
            },
            beforeMount() {
                console.log("=========beforeMount=============");
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
            },
            mounted() {
                console.log("=========mounted=============");
                console.log("html模板已渲染：" + document.getElementById("num").innerText);
            },
            beforeUpdate() {
                console.log("=========beforeUpdate=============");
                console.log("数据模型已更新：" + this.num);
                console.log("html模板未更新：" + document.getElementById("num").innerText);
            },
            updated() {
                console.log("=========updated=============");
                console.log("数据模型已更新：" + this.num);
                console.log("html模板已更新：" + document.getElementById("num").innerText);
            }
        });
    </script>
</body>

</html>
```
