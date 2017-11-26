# 设计模式


## 说明

### [单例模式]
- 保证整个应用中某个实例只有一个，需考虑多线程的情形。
- 根据不同的实现方式分成**懒汉模式**、**饿汉模式**、**枚举**和**内部静态类**。
- 补充说明 synchronized，详见[SynchronizedSample]

| 单例模式 | 多线程时是否重复创建单例对象 | 加载类的速度 | 运行时获取对象的速度 | 线程安全 | 代码链接 | 适用情形 | 其它 |
| -- | -- | -- | -- | -- | -- | -- | -- |
| 懒汉模式 | 会 | 快 | 慢 | 否 | [LazyInitializingSingleton] | 某个单例用的次数不是很多，但是这个单例提供的功能又非常复杂，而且加载和初始化要消耗大量的资源 | 与其用懒汉模式不如直接用内部静态类 |
| 懒汉模式-双重校验锁 | 否 | 快 | 慢 | 是 | [SafeLazyInitializingSingleton] | 同上 | 同上 |
| 内部静态类 | 否 | 快 | 慢 | 是 | [BillPughSingleton] | 同上 | 只要应用中不使用内部类 JVM 就不会去加载这个单例类，也就不会创建单例对象，从而实现懒汉式的延迟加载和线程安全。 |
| 饿汉模式 | 否 | 慢 | 快 | 是 | [EagerInitializingSingleton] | 单例对象初始化非常快，而且占用内存非常小的时候这种方式是比较合适的，可以直接在应用启动时加载并初始化 | -- |
| 枚举 | 否 | 慢 | 快 | 是 | [EnumSingleton] | -- | 创建枚举默认就是线程安全的，所以不需要担心双重校验锁，而且还能防止反序列化导致重新创建新的对象 |

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
- 假设用户要生产[new style robot]和[old style robot]，定义[Robot]并实现[RobotPlan]，
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
- 举例来说，用[Computer]和[Blu_ray_disc_player]来看影片，[Monitor]有两种插槽HDMI或VGA，假设数据流传到显示器做的事都一样，就是“显示画面”，这边创建一个[CableAdapter]（Adapter），根据电脑接头的类型，接到显示器相同的插槽。
- 建立一个统一的接口[MediaPlayer]，让[Computer]、[Blu_ray_disc_player]和[CableAdapter]实现，而在Computer和Blu_ray_disc_player的play()则是呼叫CableAdapter的play()方法，真正做切换的地方是CableAdapter。

```Java
private static void testAdapter() {
  MediaPlayer pc = new Computer();
  pc.play();
  MediaPlayer br = new Blu_ray_disc_player();
  br.play();
}
```

### [桥接模式]
- 举个例，假如用户要购买一辆新车，他选择任意的车型与颜色再结账（呼叫[BuyerSGuide]的addToCart()），所以在建构模组时，实作[ColorSet]和[Garage]的接口。

```Java
private static void testBridge() {
  BuyerSGuide bSGuide = new BuyerSGuide(new Hybids_N_Electric_Vehicle(), new Black());
  bSGuide.addToCart();
}
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
- 简言之，树状结构，用List < List > 实现。

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
- 比如定义一个[Command]接口，让[Attack]和[Jump]等等实现，再通过[Controller]调度。

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
- 用户输入代码后，通过Interpreter模式来处理，执行相应的功能。
- 可应用于处理SQL指令。
- 举例来说，要做一个可以处理英语句子的解释器。定义一个[Expression]接口，分别由[TerminalExpression]、[AndExpression]和[OrExpression]实现，这些是运算逻辑，接着再[Toolkits]定义规则，输入一串句子后，就能用Toolkits订的规则处理（基于稍早定义的运算逻辑）。

```Java
private static void testInterpreter() {
  Toolkits toolkits = new Toolkits();
  Expression q1 = toolkits.getStates();
  Expression q2 = toolkits.getVotingLimitation();
  System.out.println("Interpreter: Is Beijing one of a state in the United States? " + q1.interpret("Beijing"));
  System.out.println("Interpreter: Is Florida one of a state in the United States? " + q1.interpret("Florida"));

  System.out.println("Interpreter: Am I eligible to vote? " + q2.interpret("I am a kid"));
  System.out.println(
      "Interpreter: Is Mom eligible to vote? " + q2.interpret("She is adult and she is a U.S. citizen"));
}
```

### [迭代器模式]

```Java
private static void testIterator() {
  Sequence sequence = new Sequence();
  System.out.print("Iterator: ");
  for (Iterator iterator = sequence.getIterator(); iterator.hasNext();)
    System.out.print(iterator.next() + " ");
  System.out.print("\n");
}
```

### [中介者模式]
- 原本是Main直接和[ChatRoom]通信，在中介者模式中加入一个中介者[User]避免上述两者直接沟通，好处是能把沟通的行为给封装，并且未来在代码维护时，可能比较不会改动到另一方。

```Java
private static void testMediator() {
  User user = new User("A001", "Sev");
  user.sendMessage("Hi there.");
}
```

### [备忘录模式]
- 备忘录模式用于记录状态，比如游戏存档。

```Java
private static void testMemento() {
  Settings settings = new Settings();

  World village1 = new World();
  village1.setAmmo("Hunter arrow");
  village1.setWeapon("Sharpshot bow");
  village1.setXP(10000);
  village1.setOutfit("Noar survivor");
  int save1 = settings.save(village1.getState());
  System.out.println(settings.loadLatest());

  World village2 = new World();
  village2.setAmmo("Fire arrow");
  village2.setWeapon("War bow");
  village2.setXP(20000);
  village2.setOutfit("Shield weaver");
  int save2 = settings.save(village2.getState());
  System.out.println(settings.load(save1));
  System.out.println(settings.load(save2));
}
```

### [观察者模式]
- 注册几个观察者，一旦状态改变就通知所有观察者。
- 定义一个[BroadcastManager]来管理、发布通知，其它注册的观察者比如[HexObserver]、[BinaryObserver]收到通知后作出相应的处理。

```Java
private static void testObserver() {
  BroadcastManager manager = new BroadcastManager();
  new BinaryObserver(manager);
  new HexObserver(manager);
  new OctalObserver(manager);
  manager.setState(17);
  manager.setState(300);
}
```
### [观察者模式改]
- 参考Android的LocalBroadcastManager，同观察者模式，做了些调整，让用户自定义观察者收到的信息和之后的行为。
- 原先的观察者模式，收到通知后都是在个别实现观察者接口的类别里做不同的处理，改成用[Receiver]接口，让注册观察者（或理解为广播）的对象自行处理。

```Java
private static void testObserverPlus() {
  com.catherine.observer_premium.BroadcastManager manager = new com.catherine.observer_premium.BroadcastManager();
  manager.register(new Receiver() {

    @Override
    public void onReceive(String content) {
      System.out.println(String.format("Observer plus: (Main1)%s", content));
    }
  });
  manager.register(new Receiver() {

    @Override
    public void onReceive(String content) {
      System.out.println(String.format("Observer plus: (Main2)%s", content));
    }
  });
  ObserverPlusTest observerPlusTest = new ObserverPlusTest();
  observerPlusTest.registerReceiver();
  manager.sendMessage("Wake up!");

  observerPlusTest.unregisterReceiver();
  manager.sendMessage("Hurry!");
}
```

### [状态模式]
- 创建一个[Gear]接口，让几个不同的对象实现。

```Java
private static void testState() {
  Gear g = new Gear1();
  System.out.println(g.getState());

  g = new Gear2();
  System.out.println(g.getState());

  g = new GearR();
  System.out.println(g.getState());
}
```

### [空对象模式]
- 处理对象为Null的情况，不要直接报NullPointerException。
- 首先定义一个[AbstractID]的抽象类，让[RealID]和[NullID]给实现，一旦[IDChecker]在获取ID时为Null，自动返回在先前定义好的值。

```Java
private static void testNullObject() {
  IDChecker idChecker = new IDChecker();
  System.out.println(idChecker.getName("12351"));
  System.out.println(idChecker.getName("10000"));
  System.out.println(idChecker.getName("62343"));
  System.out.println(idChecker.getName("20000"));
  System.out.println(idChecker.getName("34261"));

}
```

### [策略模式]
- 策略模式的用途在于执行阶段时可进行不同的运算。

```Java
private static void testStrategy() {
  Calculator calculator = new Calculator(new OperationAdd());
  System.out.println("4 + 5 = " + calculator.execute(4, 5));

  calculator = new Calculator(new OperationSubstract());
  System.out.println("4 - 5 = " + calculator.execute(4, 5));

  calculator = new Calculator(new OperationMultiply());
  System.out.println("4 * 5 = " + calculator.execute(4, 5));
}
```

### [模板模式]
- 以程序化购买为例，每个阶段都会有些许不同，但是整体来说都是一样的步骤。
- 两个要点：1. 分别实作每个阶段；2. 用final定义模版方法（整体流程）
- 在[ProgrammaticBuying]定义每个抽象方法，再定义一个final的方法buyAds()，把每个步骤都写入（固定流程），如此只需要其它class实现抽象方法，所以，在程序化购买上[Blizzard]和[Supercell]的流程都一样，都是呼叫buyAds()，但是其中每个步骤的细节就是自行定义的。

```Java
private static void testTemplate() {
  Supercell supercell = new Supercell();
  supercell.buyAds();

  System.out.println("");

  Blizzard blizzard = new Blizzard();
  blizzard.buyAds();
}
```

### [访问者模式]
- 有一种情况是类，以[PrivateLevel]为例，内部某个方法showInfo()须常常修改，扩充这个方法只会改动方法内部的变量，和自身的类没啥关系，同时该类也很庞大或者复杂，希望在改动方法时尽量避免改到该类，可以通过访问者模式处理，让方法内部的实现在另一个类[RetrieveMethod]中处理，未来每次修改只需要更动RetrieveMethod。

```Java
private static void testVisitor() {
  System.out.println("Private");
  AccessLevel level = new PrivateLevel();
  level.showInfo(new RetrieveMethod());

  System.out.println("Protected");
  level = new ProtectedLevel();
  level.showInfo(new RetrieveMethod());

  System.out.println("Public");
  level = new PublicLevel();
  level.showInfo(new RetrieveMethod());
}
```

### [MVC 模式]
- Model-[Coupon], View-[FalseListView] 和 Controller-[RetrieveCouponFromDB]的框架，Model代表存取数据的对象或JAVA POJO，View为视图，Controller则负责控制数据流向视图，用MVC有一个好处，把业务逻辑独立开来，方便进行单元测试。

```Java
private static void testMVC() {
  RetrieveCouponFromDB rc = new RetrieveCouponFromDB();
  rc.downloadCoupons();

  // click an item
  String ID = "0001";
  rc.downloadCouponDetail(ID);

  // refresh
  rc.downloadCoupons();

  // click an item
  ID = "0002";
  rc.downloadCouponDetail(ID);
}
```

### [业务代表模式]
- 建立[BusinessDelegate]，内部创建[BusinessLookUp]对象，用来切换[EJBService]和[JMSService]，再将BusinessDelegate实体传给[Client]，由Client呼叫BusinessDelegate的方法。
- 几个优点：1. 可以把如何创建、调度服务（在[BusinessLookUp]内部实现）以及服务如何运作的部分隐藏起来。2. 可以控制服务（[EJBService]和[JMSService]）的缓存，不用管用户（Client）如何操作。

```Java
private static void testBusinessDelegate() {
  BusinessDelegate bd = new BusinessDelegate(ServiceType.EJB);
  Client client = new Client(bd);
  client.createTask();

  bd = new BusinessDelegate(ServiceType.JMS);
  client = new Client(bd);
  client.createTask();
}
```

### [组合实体模式]
- 把对象分为粗颗粒和细颗粒，在此以[DependentBread]、[DependentMeat]和[DependentSauce]为例，这些是细颗粒，[CoarseGrainedHamburger]把几个对象（细颗粒）给组合在一起管理，这是粗颗粒。
- 也可以有多个粗颗粒，这时候会建立一个对象[CompositeEntity]，获取多个粗颗粒并分别处理，比如getter和setter。
- 举个例，bread, meat, sauce等细颗粒不能单独存在，必须三者合并成为hamburger（粗颗粒）才有意义，用组合实体模式进行hamburger的设置和引用。


```Java
private static void testCompositeEntity() {
  Cashier cashier = new Cashier();
  cashier.chectOut("white", "roast beef", "BBQ");
  cashier.printReceipt();

  cashier.chectOut("honey oat", "meatball", "olive oil & salt");
  cashier.printReceipt();
}
```

### [数据访问对象模式]
- DAO(Data Access Object)：通过DAO介面存取数据，这边本来有一个BlacklistDAO接口，并且由[BlacklistDAOImpl]实现，但是为了用单例模式解决多线程修改数据的问题直接省略BlacklistDAO接口。
- 用singleton避免多线程操作数据库——static方法+synchronized代码块可以保证无论有多少线程，同时只会有一个线程执行该方法，用同一把锁锁定代码块，可以保证同时只有一个线程执行一个代码块（多线程，多种方法做方法排程，一次只执行一种方法）
- 补充说明 synchronized，详见[SynchronizedSample]

```Java
private static void testDAO() {
  BlacklistDAOImpl.getInstance();
  Contact contact1 = new Contact();
  contact1.setName("Zhang-San");
  contact1.setBlock(Contact.BLOCK_PHONE_CALL);

  Contact contact2 = new Contact();
  contact2.setName("Li-Si");
  contact2.setBlock(Contact.BLOCK_PHONE_CALL | Contact.BLOCK_SMS);

  BlacklistDAOImpl.add(contact1);
  BlacklistDAOImpl.add(contact2);
  System.out.println(String.format("(%s)Add Zhang-San and Li-Si", Thread.currentThread().getName()));
  List<Contact> blacklist = BlacklistDAOImpl.getBlacklist();
  for (int i = 0; i < blacklist.size(); i++) {
    System.out.println(String.format("(%s)%s", Thread.currentThread().getName(), blacklist.get(i)));
  }

  // test multiple treads
  Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
      BlacklistDAOImpl.getInstance();
      Contact contact3 = new Contact();
      contact3.setName("Wang-Wu");
      contact3.setBlock(Contact.BLOCK_PHONE_CALL | Contact.BLOCK_SMS);
      BlacklistDAOImpl.add(contact3);
      System.out
          .println(String.format("(%s)Add Wang-Wu in another thread", Thread.currentThread().getName()));
      List<Contact> blacklist = BlacklistDAOImpl.getBlacklist();
      for (int i = 0; i < blacklist.size(); i++) {
        System.out.println(String.format("(%s)%s", Thread.currentThread().getName(), blacklist.get(i)));
      }
    }
  });
  t.start();

  Contact contact = blacklist.get(1);
  System.out.println(String.format("(%s)Update Li-Si", Thread.currentThread().getName()));
  contact.setName("Li-Si");
  contact.setBlock(Contact.BLOCK_SMS);
  BlacklistDAOImpl.update(contact);
  blacklist = BlacklistDAOImpl.getBlacklist();
  for (int i = 0; i < blacklist.size(); i++) {
    System.out.println(String.format("(%s)%s", Thread.currentThread().getName(), blacklist.get(i)));
  }

  blacklist = BlacklistDAOImpl.getBlacklist();
  System.out.println(
      String.format("(%s)Delete _id=%d", Thread.currentThread().getName(), blacklist.get(1).getID()));
  BlacklistDAOImpl.delete(blacklist.get(1).getID());
  blacklist = BlacklistDAOImpl.getBlacklist();
  for (int i = 0; i < blacklist.size(); i++) {
    System.out.println(String.format("(%s)%s", Thread.currentThread().getName(), blacklist.get(i)));
  }
}
```

### [前端控制器模式]
- 通过调度器返回不同的视图或处理程序。

```Java
private static void testFrontController() {
  FrontController fController = new FrontController();
  String token = null;
  fController.dispatchView(token);

  token = fController.inputCaptcha("XI0dk3");
  fController.dispatchView(token);

  token = fController.login("Oleg1234", "pw1234");
  fController.dispatchView(token);
}
```

### [拦截过滤器模式]
- 拦截过滤器模式通过过滤器链处理多过滤器，和过滤器模式一样自定义过滤器的接口让每个过滤的类别各自实现。
- 原始的逻辑是Main直接通过[MusicPlayer]呼叫getArtist(int)，加入拦截过滤器后变成通过MusicPlayer呼叫getArtist([FilterManager], MemberInfo)。

```Java
private static void testInterceptingFilter() {
  MemberInfo user1 = new MemberInfo();
  user1.setID(1);
  user1.setLevel(Level.BASIC);
  user1.setName("0001");
  user1.setCountry(Country.US);

  MemberInfo user2 = new MemberInfo();
  user2.setID(2);
  user2.setLevel(Level.BASIC);
  user2.setName("0002");
  user2.setCountry(Country.UK);

  MemberInfo user3 = new MemberInfo();
  user3.setID(3);
  user3.setLevel(Level.STANDARD);
  user3.setName("0003");
  user3.setCountry(Country.US);

  MemberInfo user4 = new MemberInfo();
  user4.setID(4);
  user4.setLevel(Level.PRIMIUM);
  user4.setName("0004");
  user4.setCountry(Country.UK);

  MemberInfo[] infos = new MemberInfo[] { user1, user2, user3, user4 };
  MusicPlayer player = new MusicPlayer();
  FilterManager fm = new FilterManager();
  fm.addFilter(new LevelFilter(Level.BASIC));
  fm.addFilter(new CountryFilter(Country.UK));
  // fm.addFilter(new DebuggerFilter());
  for (int i = 0; i < infos.length; i++) {
    List<String> playlist = player.getArtist(fm, infos[i]);
    if (playlist.size() != 0)
      System.out.println(playlist);
  }
}
```

### [服务定位器模式]
- 服务定位器模式用在一种情况，假设用户访问某service获取数据，此时将该service做缓存，下次访问时直接导向缓存以提高效能。
- 通过[ServiceLocator]获取服务，如果有存在[Cache]就直接调用，没有才通过[InitialService]

```Java
private static void testServiceLocator() {
  ServiceLocator s1 = new ServiceLocator();
  Service pService1 = s1.lookup("playlist");
  Service hService1 = s1.lookup("history");
  System.out.println(pService1.response(1));
  System.out.println(hService1.response(1));

  ServiceLocator s2 = new ServiceLocator();
  Service pService2 = s2.lookup("playlist");
  Service hService2 = s2.lookup("history");
  System.out.println(pService2.response(2));
  System.out.println(hService2.response(2));
}
```

### [传输对象模式]
- 比如一间工作室[Studio]有一堆员工[Employee]，建立Employee对象，让Studio管理多个Employee。

```Java
private static void testTransferObject() {
  com.catherine.transfer_object.Employee Thomos = new com.catherine.transfer_object.Employee();
  Thomos.setID(1);
  Thomos.setName("Thomos");
  Thomos.setDept("Present");

  com.catherine.transfer_object.Employee Jeff = new com.catherine.transfer_object.Employee();
  Jeff.setID(2);
  Jeff.setName("Jeff");
  Jeff.setDept("Director");

  com.catherine.transfer_object.Employee Jonas = new com.catherine.transfer_object.Employee();
  Jonas.setID(3);
  Jonas.setName("Jonas");
  Jonas.setDept("Marketing");
  Studio studio = new Studio();
  studio.addEmployee(Thomos);
  studio.addEmployee(Jeff);
  studio.addEmployee(Jonas);
  studio.showAllStuff();

  System.out.println("update Jonas");
  studio.updateEmployee(3, "Head marketing");
  studio.showAllStuff();

  System.out.println("fire Thomos");
  studio.fireEmployee(1);
  studio.showAllStuff();
}
```

## 补充说明

### [synchronized] 示范

```Java
private static void testSynchronized() {
  SynchronizedSample ss = new SynchronizedSample();
  ss.testStaticParamsAsUsual();
  ss.testStaticParams();
  ss.testStaticParamsFromDifferentObj();
  ss.testStaticMethod();
  ss.testStaticMethodAndSyncCodes();
}
```

### MVP 模式（Android专用）
- [Google MVP Sample]

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
[解释器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/>
[迭代器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/iterator/>
[中介者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mediator/>
[备忘录模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/memento/>
[观察者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer/>
[观察者模式改]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer_premium/>
[状态模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/state/>
[空对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/null_object/>
[策略模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/strategy/>
[模板模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/template/>
[访问者模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/visitor/>
[MVC 模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mvc/>
[业务代表模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/>
[组合实体模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/>
[数据访问对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/data_access_object/>
[前端控制器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/front_controller/>
[拦截过滤器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/intercepting_filter/>
[服务定位器模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/service_locator/>
[传输对象模式]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/transfer_object/>
[MVP 模式（Android专用）]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/flyweight/>
[synchronized]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/singleton/SynchronizedSample.java>
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
[new style robot]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/NewStyleRobotBuilder.java>
[old style robot]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/builder/OldStyleRobotBuilder.java>
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
[Blu_ray_disc_player]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/Blu_ray_disc_player.java>
[MediaPlayer]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/adapter/MediaPlayer.java>
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
[Expression]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/Expression.java>
[TerminalExpression]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/TerminalExpression.java>
[AndExpression]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/AndExpression.java>
[OrExpression]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/OrExpression.java>
[Toolkits]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/interpreter/Toolkits.java>
[ChatRoom]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mediator/ChatRoom.java>
[User]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mediator/User.java>
[BroadcastManager]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer/BroadcastManager.java>
[HexObserver]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer/HexObserver.java>
[BinaryObserver]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer/BinaryObserver.java>
[Receiver]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/observer_premium/Receiver.java>
[Gear]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/state/Gear.java>
[GearR]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/state/GearR.java>
[AbstractID]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/null_object/AbstractID.java>
[RealID]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/null_object/RealID.java>
[NullID]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/null_object/NullID.java>
[IDChecker]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/null_object/IDChecker.java>
[ProgrammaticBuying]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/template/ProgrammaticBuying.java>
[Blizzard]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/template/Blizzard.java>
[Supercell]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/template/Supercell.java>
[PrivateLevel]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/visitor/PrivateLevel.java>
[RetrieveMethod]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/visitor/RetrieveMethod.java>
[FalseListView]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mvc/FalseListView.java>
[Coupon]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mvc/models/Coupon.java>
[RetrieveCouponFromDB]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/mvc/RetrieveCouponFromDB.java>
[BusinessDelegate]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/BusinessDelegate.java>
[Client]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/Client.java>
[BusinessLookUp]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/BusinessLookUp.java>
[EJBService]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/EJBService.java>
[JMSService]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/business_delegate/JMSService.java>
[Cashier]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/Cashier.java>
[CoarseGrainedHamburger]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/CoarseGrainedHamburger.java>
[CompositeEntity]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/CompositeEntity.java>
[DependentBread]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/DependentBread.java>
[DependentMeat]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/DependentMeat.java>
[DependentSauce]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/composite_entity/DependentSauce.java>
[BlacklistDAOImpl]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/data_access_object/BlacklistDAOImpl.java>
[MusicPlayer]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/intercepting_filter/MusicPlayer.java>
[FilterManager]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/intercepting_filter/FilterManager.java>
[ServiceLocator]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/service_locator/ServiceLocator.java>
[Cache]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/service_locator/Cache.java>
[InitialService]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/service_locator/InitialService.java>
[Studio]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/transfer_object/Studio.java>
[Employee]:<https://github.com/Catherine22/DesignPattern/tree/master/src/com/catherine/transfer_object/Employee.java>
[Google MVP Sample]:<https://github.com/googlesamples/android-architecture>
