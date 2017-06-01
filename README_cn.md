# 设计模式

[English]


## 说明

### 单例模式

- 保证整个应用中某个实例只有一个，需考虑多线程的情形。
- 根据不同的实现方式分成**懒汉模式**、**饿汉模式**、**枚举**和**内部静态类**。

| 单例模式 | 多线程时是否重复创建单例对象 | 加载类的速度 | 运行时获取对象的速度 | 线程安全 | 代码链接 | 适用情形 | 其它 |
| -- | -- | -- | -- | -- | -- | -- | -- |
| 懒汉模式 | 会 | 快 | 慢 | 否 | [LazyInitializingSingleton] | 某个单例用的次数不是很多，但是这个单例提供的功能又非常复杂，而且加载和初始化要消耗大量的资源 | 与其用懒汉模式不如直接用内部静态类 |
| 懒汉模式-双重校验锁 | 否 | 快 | 慢 | 是 | [SafeLazyInitializingSingleton] | 同上 | 同上 |
| 内部静态类 | 否 | 快 | 慢 | 是 | [BillPughSingleton] | 同上 | 只要应用中不使用内部类 JVM 就不会去加载这个单例类，也就不会创建单例对象，从而实现懒汉式的延迟加载和线程安全。 |
| 饿汉模式 | 否 | 慢 | 快 | 是 | [EagerInitializingSingleton] | 单例对象初始化非常快，而且占用内存非常小的时候这种方式是比较合适的，可以直接在应用启动时加载并初始化 | -- |
| 枚举 | 否 | 慢 | 快 | 是 | [EnumSingleton] | -- | 创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象 |

- **一般情况下直接使用饿汉模式就好了，如果明确要求要懒加载（lazy initialization）会倾向于使用静态内部类，如果涉及到反序列化创建对象时会试着使用枚举的方式来实现单例。**


### 工厂模式
- 建立一个染料（对象）工厂，用户指定各种不同的颜色（实现颜色接口）让工厂生产。
- 用户向[对象工厂]要颜色，由[红色]、[蓝色]或其它自订对象实现[颜色接口]。

### 抽象工厂模式
- 抽象工厂用在有大量工厂时，大致上与工厂模式一样，可理解成超级工厂模式。
- [超级工厂]内有各式工厂，工厂内部运作同工厂模式。

### 建造者模式
- 经过一系列的步骤完成一个复杂的对象。
- 假设用户要生产[新型机器人]和[旧式机器人]，定义[机器人]并实现[RobotPlan]，
制作[机器人]的步骤都一样（用[RobotBuilder]定义），所以两者只需要实现[RobotBuilder]接口，
用户要生产时，直接和[RobotDirector]沟通即可，[RobotDirector]已经定义好制作机器人的流程。


## 参考来源
- [runoob.com]
- [tutorialspoint]

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

[runoob.com]:<https://www.tutorialspoint.com/design_pattern/index.htm>
[]:<http://www.runoob.com/design-pattern/design-pattern-tutorial.html>
[English]:<https://github.com/Catherine22/DesignPattern/blob/master/README.md>
[LazyInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/LazyInitializingSingleton.java>
[SafeLazyInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/SafeLazyInitializingSingleton.java>
[BillPughSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/BillPughSingleton.java>
[EagerInitializingSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/EagerInitializingSingleton.java>
[EnumSingleton]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/singleton/EnumSingleton.java>
[对象工厂]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/factory/ColorFactory.java>
[颜色接口]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Color.java>
[红色]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Red.java>
[蓝色]:<https://github.com/Catherine22/DesignPattern/blob/master/src/com/catherine/factory/Blue.java>
[超级工厂]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/abstract_factory/CarFactory.java>
[新型机器人]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/NewStyleRobotBuilder.java>
[旧式机器人]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/OldStyleRobotBuilder.java>
[RobotBuilder]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotBuilder.java>
[机器人]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/Robot.java>
[RobotPlan]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotPlan.java>
[RobotDirector]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/RobotDirector.java>
