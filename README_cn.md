# 设计模式

[English]


## 说明

### [单例模式]

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

```Java
private static void testSingleton() {
  BillPughSingleton bInstance1 = BillPughSingleton.getInstance();
  // bInstance1.print();

  EagerInitializingSingleton eInstance1 = EagerInitializingSingleton.getInstance();
  // eInstance1.print();

  EnumSingleton eunm1 = EnumSingleton.INSTANCE;
  // eInstance1.print();

  LazyInitializingSingleton lInstance1 = LazyInitializingSingleton.getInstance();
  // lInstance1.print();

  SafeLazyInitializingSingleton sInstance1 = SafeLazyInitializingSingleton.getInstance();
  // sInstance1.print();

  Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
      BillPughSingleton bInstance2 = BillPughSingleton.getInstance();
      EagerInitializingSingleton eInstance2 = EagerInitializingSingleton.getInstance();
      EnumSingleton eunm2 = EnumSingleton.INSTANCE;
      LazyInitializingSingleton lInstance2 = LazyInitializingSingleton.getInstance();
      SafeLazyInitializingSingleton sInstance2 = SafeLazyInitializingSingleton.getInstance();

      SingletonTest sTest = new SingletonTest();
      if (bInstance1 == bInstance2 && bInstance1 == sTest.getBillPughSingleton())
        System.out.println("Singleton\tBillPughSingleton 同一个实例");
      else
        System.out.println("Singleton\tBillPughSingleton 不同实例");

      if (eInstance1 == eInstance2 && eInstance1 == sTest.getEagerInitializingSingleton())
        System.out.println("Singleton\tEagerInitializingSingleton 同一个实例");
      else
        System.out.println("Singleton\tEagerInitializingSingleton 不同实例");

      if (eunm1 == eunm2 && eunm1 == sTest.getEnumSingleton())
        System.out.println("Singleton\tEnumSingleton 同一个实例");
      else
        System.out.println("Singleton\tEnumSingleton 不同实例");

      if (lInstance1 == lInstance2 && lInstance1 == sTest.getLazyInitializingSingleton())
        System.out.println("Singleton\tLazyInitializingSingleton 同一个实例");
      else
        System.out.println("Singleton\tLazyInitializingSingleton 不同实例");

      if (sInstance1 == sInstance2 && sInstance1 == sTest.getSafeLazyInitializingSingleton())
        System.out.println("Singleton\tSafeLazyInitializingSingleton 同一个实例");
      else
        System.out.println("Singleton\tSafeLazyInitializingSingleton 不同实例");
    }
  });
  t.start();
}
```

### [工厂模式]
- 比如要生产一个部件，内部构造一摸一样，唯有颜色不同，那就定义一个[Color1]接口，用户向[ColorFactory]要颜色，由[Red1]、[Blue1]或其它自订对象实现[Color1]。

```Java
private static void testFactory() {
		ColorFactory cf = new ColorFactory();
		cf.getColor(ColorFactory.BLUE).onDraw();
		cf.getColor(ColorFactory.RED).onDraw();
	}
```

### [抽象工厂模式]
- 抽象工厂用在有大量工厂时，大致上与工厂模式一样，可理解成超级工厂模式。
- [super factory]内有各式工厂，工厂内部运作同工厂模式。

```Java
private static void testAbstractFactory() {
		CarFactory cf = new CarFactory();
		cf.getColor(CarFactory.RED).onDraw();
		cf.getBrand(CarFactory.BENTLEY).show();
	}
```

### [建造者模式]
- 经过一系列的步骤完成一个复杂的对象。
- 假设用户要生产[NewStyleRobotBuilder]和[OldStyleRobotBuilder]，定义[Robot]并实现[RobotPlan]，
制作[Robot]的步骤都一样（用[RobotBuilder]定义），所以两者只需要实现[RobotBuilder]接口，
用户要生产时，直接和[RobotDirector]沟通即可，[RobotDirector]已经定义好制作机器人的流程。

```Java
private static void testBuilder() {
		RobotDirector rd = new RobotDirector(new OldStyleRobotBuilder());
		rd.makeRobot();
		Robot robot = rd.getRobot();
		System.out.println("Builder\t" + robot.getArms());
		System.out.println("Builder\t" + robot.getHead());
		System.out.println("Builder\t" + robot.getLegs());
		System.out.println("Builder\t" + robot.getTorso());
	}
```

### [原型模式]
- 假如物件创建时耗费大量资源，用户不希望每次使用时都要重新创建，用prototype模式可以只创建一次，以后要用都用克隆。
- 其实就是拷贝的意思，但是**不是返回内存地址的引用，而是一个拷贝的物件，拥有独立的内存空间**。
- 用户通过[ColorCache]获取颜色，[Blue2]和[Red2]只需创建一次，每次存取时都是拿到克隆（返回本体的新拷贝，但无论如何本体都是安全的，不会被修改到）。

```Java
private static void testPrototype() {
		try {
			ColorCache colorCache = new ColorCache();
			Color blue = colorCache.getColor(Type.BLUE);
			blue.printID();
			Color red1 = colorCache.getColor(Type.RED);
			red1.printID();
			Color red2 = colorCache.getColor(Type.RED);
			red2.resetID();
			red2.printID();
			System.out.println(red1);
			System.out.println(red2);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
```

### [适配器模式]
- 举例来说，用[Computer]来看影片，[Monitor]有两种插槽HDMI或VGA，假设数据流传到显示器做的事都一样，就是“显示画面”，这边创建一个[CableAdapter]（Adapter），根据电脑接头的类型，接到显示器相同的插槽。

```Java
private static void testAdapter() {
		MediaPlayer pc = new Computer();
		pc.play();
		MediaPlayer br = new Blu_ray_disc_player();
		br.play();
	};
```

### [桥接模式]
- 举个例，假如用户要购买一辆新车，他选择任意的车型与颜色再结账（呼叫[BuyerSGuide]的addToCart()），所以在建构模组时，实作[ColorSet]和[Garage]的接口。

```Java
private static void testBridge() {
		BuyerSGuide bSGuide = new BuyerSGuide(new Hybids_N_Electric_Vehicle(), new Black());
		bSGuide.addToCart();
	};
```

### [过滤器模式]
- 自定义过滤条件来过滤一群物件，自定义过滤器的接口让每个过滤的类别各自实现，过滤器模式着眼于不同过滤条件的[AndCriteria]、[OrCriteria]或其它逻辑运算。

```Java
private static void testFilter() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Robert", "Male", "Single"));
		persons.add(new Person("John", "Male", "Married"));
		persons.add(new Person("Laura", "Female", "Married"));
		persons.add(new Person("Diana", "Female", "Single"));
		persons.add(new Person("Mike", "Male", "Single"));
		persons.add(new Person("Bobby", "Male", "Single"));

		Criteria single = new CriteriaSingle();
		System.out.println("Single:" + single.meetCriteria(persons));
		Criteria women = new CriteriaFemale();
		System.out.println("Women:" + women.meetCriteria(persons));
		Criteria singleWomen = new AndCriteria(single, women);
		System.out.println("Single Women:" + singleWomen.meetCriteria(persons));

		Criteria married = new CriteriaMarried();
		System.out.println("Married:" + married.meetCriteria(persons));
		Criteria men = new CriteriaMale();
		System.out.println("Men:" + men.meetCriteria(persons));
		Criteria marriedOrMen = new OrCriteria(married, men);
		System.out.println("Married or Men:" + marriedOrMen.meetCriteria(persons));
	}
```

### [组合模式]
- 简言之，树状结构，用List<List>实现。

```Java
public static void testComposite() {
		Employee ceo = new Employee("Catherine", "CEO", 100000);
		Employee headSales = new Employee("Conan", "Head sales", 80000);
		Employee headMarketing = new Employee("Jordan", "Head marketing", 80000);
		Employee sales1 = new Employee("Elsa", "Sales", 40000);
		Employee sales2 = new Employee("Anna", "Sales", 40000);
		Employee marketing = new Employee("Laura", "Marketing", 40000);

		ceo.add(headMarketing);
		ceo.add(headSales);
		headMarketing.add(marketing);
		headSales.add(sales1);
		headSales.add(sales2);
		System.out.println("CEO's subordinates:" + ceo.getSubordinates());
		System.out.println("headSales' subordinates:" + headSales.getSubordinates());
		System.out.println("headMarketing's subordinates:" + headMarketing.getSubordinates());
	}
```

### [装饰器模式]
- 建立一个[Car]的接口，用各品牌去实现show()， 装饰者模式的用意在于不破坏本体的模组前提下，对其进行修改，比如升级音响、换轮胎等，**有点类似补丁的概念**。
- 必须实现本体是重点，见[AbstractDecorator]。

```Java
private static void testDecorator() {
		Car tesla = new Tesla();
		tesla.show();
		System.out.print("normal Stereo System,\t");
		System.out.print("normal Wheels,\t");
		System.out.print("\n");
		AbstractDecorator teslaWithNewStereo = new StereoSystem(tesla);
		teslaWithNewStereo.show();
		System.out.print("normal Wheels,\t");
		System.out.print("\n");
		AbstractDecorator teslaWithNewWheels = new Wheels(tesla);
		teslaWithNewWheels.show();
		System.out.print("normal Stereo System,\t");
		System.out.print("\n");
		AbstractDecorator teslaWithNewWheelsNStereo = new Wheels(teslaWithNewStereo);
		teslaWithNewWheelsNStereo.show();
		System.out.print("\n");
	}
```

### [外观模式]
- 外观模式简单来说就是把内部实现的方法包装后再让用户呼叫（原本是用户直接呼叫），优点是未来内部实现的类有变动时，用户端可能可以不用更动。
- 比如呼叫[Convertible]或[Coupe]内部的方法原始做法会直接呼叫，但用外观模式的做法就会在两者外再包一层class，呼叫时是通过该class（[CarFactory]）。

```Java
private static void testFacade() {
		com.catherine.facade.CarFactory cFactory = new com.catherine.facade.CarFactory();
		cFactory.buildCoupe();
		cFactory.buildConvertible();
	}
```

### [享元模式]
- Flyweight模式的用意在于减少内存的使用。
- 好比要画出二十个圆形，总共只有五种颜色随意填充，在[ShapeFactory]创建一个HashMap并以颜色作为key，一共只需要创建五个物件。

```Java
private static void testFlyweight() {
		final String[] colors = { "BLUE", "BLACK", "YELLOW", "GREEN", "WHITE" };
		ShapeFactory sf = new ShapeFactory();
		// 随机产生20个圆
		for (int i = 0; i < 20; i++) {
			Circle circle = sf.getCircle(colors[(int) (Math.random() * colors.length)]);
			circle.setRadius((int) (Math.random() * 100));
			circle.draw();
		}
		sf.debug();
	}
```

### [代理模式]
- 假设我们要加载一张图，在[ImageLoader]初始化时加载，我们不希望每次加载同一张图都要重新创建一个ImageLoader（因为初始化费时之类的），利用一个[ImageLoaderProxy]来加载，一旦有了初始化过后的ImageLoader，就会用原先的ImageLoader物件继续操作。

```Java
private static void testProxy() {
		ImageLoaderProxy imageLoader = new ImageLoaderProxy();
		imageLoader.display();
		imageLoader.display();
	}
```

### [责任链模式]
- 打印log时，权重最高的ERROR在较次的权重过滤器中都能打印，反之权重最低的DEBUG，在其它权重过滤器时都不会被打印。先设置[Logger]链，在用的时候一律执行[ErrorLogger]的实例，呼叫logMessage()时就会带入log级别。

```Java
private static void testChainOfResponsibility() {
  // 先设置logger链，在用的时候一律执行ErrorLogger的实例，呼叫logMessage时就会带入log级别
  Logger logger = new ErrorLogger();
  Logger wLogger = new WarningLogger();
  Logger dLogger = new DebugLogger();
  logger.setNextLogger(wLogger);
  wLogger.setNextLogger(dLogger);

  // 真正开始测试
  logger.logMessage(Logger.ERROR, "crash");
  logger.logMessage(Logger.WARNING, "error pages");
  logger.logMessage(Logger.DEBUG, "change color");
}
```

### [命令模式]
- 比如我定义一个[Command]接口，让[Attack]和[Jump]等等实现，再通过[Controller]调度。

```Java
private static void testCommand() {
		Command attack = new Attack();
		Command jump = new Jump();
		Command dodge = new Dodge();

		Controller controller = new Controller();
		System.out.print("Command: roll: ");
		controller.add(dodge);
		controller.add(jump);
		controller.combos();

		System.out.print("Command: aerial dash attack: ");
		controller.add(jump);
		controller.add(attack);
		controller.combos();
	}
```

### [解释器模式]

```Java

```

### [迭代器模式]

```Java

```

### [中介者模式]

```Java

```

### [备忘录模式]

```Java

```

### [观察者模式]

```Java

```

### [状态模式]

```Java

```

### [空对象模式]

```Java

```

### [策略模式]

```Java

```

### [模板模式]

```Java

```

### [访问者模式]

```Java

```

### [MVC 模式]

```Java

```

### [业务代表模式]

```Java

```

### [组合实体模式]

```Java

```

### [数据访问对象模式]
- 补充说明synchronized，详见[SynchronizedSample]

```Java

```

### [前端控制器模式]

```Java

```

### [拦截过滤器模式]

```Java

```

### [服务定位器模式]

```Java

```

### [传输对象模式]

```Java

```

### [MVP 模式（Android专用）]

```Java

```

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
[组合模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite/>
[装饰器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/decorator/>
[外观模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/facade/>
[享元模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[代理模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/proxy/>
[责任链模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/chain_of_responsibility/>
[命令模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/command/>
[解释器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[迭代器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[中介者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[备忘录模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[观察者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[状态模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[空对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[策略模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[模板模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[访问者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[MVC 模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[业务代表模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[组合实体模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[数据访问对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[前端控制器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[拦截过滤器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[服务定位器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[传输对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
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
[Car]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/decorator/Car.java>
[AbstractDecorator]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/decorator/AbstractDecorator.java>
[CarFactory]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/facade/CarFactory.java>
[Convertible]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/facade/Convertible.java>
[Coupe]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/facade/Coupe.java>
[ShapeFactory]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/ShapeFactory.java>
[ImageLoader]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/proxy/ImageLoader.java>
[ImageLoaderProxy]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/proxy/ImageLoaderProxy.java>
[ErrorLogger]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/chain_of_responsibility/ErrorLogger.java>
[Logger]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/chain_of_responsibility/Logger.java>
[Command]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/command/Command.java>
[Attack]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/command/Attack.java>
[Jump]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/command/Jump.java>
[Controller]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/command/Attack.java>
