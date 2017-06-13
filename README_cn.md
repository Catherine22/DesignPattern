# 设计模式

[English]


## 说明

### 单例模式

- 保证整个应用中某个实例只有一个，需考虑多线程的情形。
- 根据不同的实现方式分成**懒汉模式**、**饿汉模式**、**枚举**和**内部静态类**。
- 补充说明synchronized，详见[SynchronizedSample]

| 单例模式 | 多线程时是否重复创建单例对象 | 加载类的速度 | 运行时获取对象的速度 | 线程安全 | 代码链接 | 适用情形 | 其它 |
| -- | -- | -- | -- | -- | -- | -- | -- |
| 懒汉模式 | 会 | 快 | 慢 | 否 | [LazyInitializingSingleton] | 某个单例用的次数不是很多，但是这个单例提供的功能又非常复杂，而且加载和初始化要消耗大量的资源 | 与其用懒汉模式不如直接用内部静态类 |
| 懒汉模式-双重校验锁 | 否 | 快 | 慢 | 是 | [SafeLazyInitializingSingleton] | 同上 | 同上 |
| 内部静态类 | 否 | 快 | 慢 | 是 | [BillPughSingleton] | 同上 | 只要应用中不使用内部类 JVM 就不会去加载这个单例类，也就不会创建单例对象，从而实现懒汉式的延迟加载和线程安全。 |
| 饿汉模式 | 否 | 慢 | 快 | 是 | [EagerInitializingSingleton] | 单例对象初始化非常快，而且占用内存非常小的时候这种方式是比较合适的，可以直接在应用启动时加载并初始化 | -- |
| 枚举 | 否 | 慢 | 快 | 是 | [EnumSingleton] | -- | 创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象 |

- **一般情况下直接使用饿汉模式就好了，如果明确要求要懒加载（lazy initialization）会倾向于使用静态内部类，如果涉及到反序列化创建对象时会试着使用枚举的方式来实现单例。**


### [单例模式]
- 建立一个染料（对象）工厂，用户指定各种不同的颜色（实现颜色接口）让工厂生产。
- 用户向[ColorFactory]要颜色，由[Red1]、[Blue1]或其它自订对象实现[Color1]。

### [工厂模式]
- 比如要生产一个部件，内部构造一摸一样，唯有颜色不同，那就定义一个[Color1]接口，分别实现[Blue1]和[Red1]。

### [抽象工厂模式]
- 抽象工厂用在有大量工厂时，大致上与工厂模式一样，可理解成超级工厂模式。
- [super factory]内有各式工厂，工厂内部运作同工厂模式。

### [建造者模式]
- 经过一系列的步骤完成一个复杂的对象。
- 假设用户要生产[NewStyleRobotBuilder]和[OldStyleRobotBuilder]，定义[Robot]并实现[RobotPlan]，
制作[Robot]的步骤都一样（用[RobotBuilder]定义），所以两者只需要实现[RobotBuilder]接口，
用户要生产时，直接和[RobotDirector]沟通即可，[RobotDirector]已经定义好制作机器人的流程。

### [原型模式]
- 假如物件创建时耗费大量资源，用户不希望每次使用时都要重新创建，用prototype模式可以只创建一次，以后要用都用克隆。
- 其实就是拷贝的意思，但是**不是返回内存地址的引用，而是一个拷贝的物件，拥有独立的内存空间**。
- 用户通过[ColorCache]获取颜色，[Blue2]和[Red2]只需创建一次，每次存取时都是拿到克隆（返回本体的新拷贝，但无论如何本体都是安全的，不会被修改到）

### [适配器模式]
- 举例来说，用[Computer]来看影片，[Monitor]有两种插槽HDMI或VGA，假设数据流传到显示器做的事都一样，就是“显示画面”，这边创建一个[CableAdapter]（Adapter），根据电脑接头的类型，接到显示器相同的插槽。

### [桥接模式]
- 举个例，假如用户要购买一辆新车，他选择任意的车型与颜色再结账（呼叫[BuyerSGuide]的addToCart()），所以在建构模组时，实作[ColorSet]和[Garage]的接口。

### [过滤器模式]
- 自定义过滤条件来过滤一群物件，自定义过滤器的接口让每个过滤的类别各自实现，过滤器模式着眼于不同过滤条件的[AndCriteria]、[OrCriteria]或其它逻辑运算。

### 数据访问对象模式
- 补充说明synchronized，详见[SynchronizedSample]

## 参考来源
- [runoob.com]
- [tutorialspoint]
- [深入理解Java并发之synchronized实现原理]

## 开源授权协议

  ```
  Copyright 2017 Catherine Chen (https://github.com/Catherine22)

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy of
  the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations under
  the License.
  ```

[tutorialspoint]:<https://www.tutorialspoint.com/design_pattern/index.htm>
[runoob.com]:<http://www.runoob.com/design-pattern/design-pattern-tutorial.html>
[深入理解Java并发之synchronized实现原理]:<http://blog.csdn.net/javazejian/article/details/72828483>
[English]:<https://github.com/Catherine22/DesignPattern/blob/master/README.md>
[单例模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/singleton/>
[工厂模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/factory/>
[抽象工厂模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/abstract_factory/>
[建造者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/>
[原型模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/>
[适配器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/>
[桥接模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/bridge/>
[过滤器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/filter/>
[LazyInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/LazyInitializingSingleton.java>
[SafeLazyInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/SafeLazyInitializingSingleton.java>
[BillPughSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/BillPughSingleton.java>
[EagerInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/EagerInitializingSingleton.java>
[EnumSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/EnumSingleton.java>
[SynchronizedSample]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/SynchronizedSample.java>
[ColorFactory]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/factory/ColorFactory.java>
[Color1]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Color.java>
[Red1]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Red.java>
[Blue1]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Blue.java>
[super factory]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/abstract_factory/CarFactory.java>
[NewStyleRobotBuilder]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/NewStyleRobotBuilder.java>
[OldStyleRobotBuilder]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/OldStyleRobotBuilder.java>
[RobotBuilder]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotBuilder.java>
[Robot]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/Robot.java>
[RobotPlan]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotPlan.java>
[RobotDirector]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotDirector.java>
[RobotDirector]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/ColorCache.java>
[ColorCache]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/ColorCache.java>
[Color2]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/Color.java>
[Red2]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/Red.java>
[Blue2]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/prototype/Blue.java>
[Computer]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/Computer.java>
[Monitor]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/Monitor.java>
[CableAdapter]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/CableAdapter.java>
[Garage]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/bridge/Garage.java>
[ColorSet]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/bridge/ColorSet.java>
[BuyerSGuide]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/bridge/BuyerSGuide.java>
[OrCriteria]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/filter/OrCriteria.java>
[AndCriteria]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/filter/AndCriteria.java>
