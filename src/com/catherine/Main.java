package com.catherine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.catherine.abstract_factory.CarFactory;
import com.catherine.adapter.Blu_ray_disc_player;
import com.catherine.adapter.Cable;
import com.catherine.adapter.Computer;
import com.catherine.adapter.MediaPlayer;
import com.catherine.bridge.Black;
import com.catherine.bridge.BuyerSGuide;
import com.catherine.bridge.Hybids_N_Electric_Vehicle;
import com.catherine.builder.OldStyleRobotBuilder;
import com.catherine.builder.Robot;
import com.catherine.builder.RobotDirector;
import com.catherine.chain_of_responsibility.DebugLogger;
import com.catherine.chain_of_responsibility.ErrorLogger;
import com.catherine.chain_of_responsibility.Logger;
import com.catherine.chain_of_responsibility.WarningLogger;
import com.catherine.command.Attack;
import com.catherine.command.Command;
import com.catherine.command.Controller;
import com.catherine.command.Dodge;
import com.catherine.command.Jump;
import com.catherine.composite.Employee;
import com.catherine.decorator.AbstractDecorator;
import com.catherine.decorator.Car;
import com.catherine.decorator.StereoSystem;
import com.catherine.decorator.Tesla;
import com.catherine.decorator.Wheels;
import com.catherine.factory.ColorFactory;
import com.catherine.filter.AndCriteria;
import com.catherine.filter.OrCriteria;
import com.catherine.filter.Person;
import com.catherine.filter.criteria.Criteria;
import com.catherine.filter.criteria.CriteriaFemale;
import com.catherine.filter.criteria.CriteriaMale;
import com.catherine.filter.criteria.CriteriaMarried;
import com.catherine.filter.criteria.CriteriaSingle;
import com.catherine.flyweight.Circle;
import com.catherine.flyweight.Shape;
import com.catherine.flyweight.ShapeFactory;
import com.catherine.interpreter.Expression;
import com.catherine.interpreter.Toolkits;
import com.catherine.mediator.User;
import com.catherine.memento.Settings;
import com.catherine.memento.World;
import com.catherine.observer.BinaryObserver;
import com.catherine.observer.BroadcastManager;
import com.catherine.observer.HexObserver;
import com.catherine.observer.OctalObserver;
import com.catherine.observer_premium.Receiver;
import com.catherine.prototype.Color;
import com.catherine.prototype.ColorCache;
import com.catherine.prototype.Type;
import com.catherine.proxy.ImageLoaderProxy;
import com.catherine.singleton.BillPughSingleton;
import com.catherine.singleton.EagerInitializingSingleton;
import com.catherine.singleton.EnumSingleton;
import com.catherine.singleton.LazyInitializingSingleton;
import com.catherine.singleton.SafeLazyInitializingSingleton;
import com.catherine.state.Gear;
import com.catherine.state.Gear1;
import com.catherine.state.Gear2;
import com.catherine.state.GearR;

import iterator.Iterator;
import iterator.Sequence;

public class Main {

	public static void main(String[] args) {
		// testSingleton();
		// testFactory();
		// testAbstractFactory();
		// testBuilder();
		// testPrototype();
		// testAdapter();
		// testBridge();
		// testFilter();
		// testComposite();
		// testDecorator();
		// testFacade();
		// testFlyweight();
		// testProxy();
		// testChainOfResponsibility();
		// testCommand();
		// testInterpreter();
		// testIterator();
		// testMediator();
		// testMemento();
		// testObserver();
		// testObserverPlus();
		testState();

	}

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

	private static void testFactory() {
		ColorFactory cf = new ColorFactory();
		cf.getColor(ColorFactory.BLUE).onDraw();
		cf.getColor(ColorFactory.RED).onDraw();
	}

	private static void testAbstractFactory() {
		CarFactory cf = new CarFactory();
		cf.getColor(CarFactory.RED).onDraw();
		cf.getBrand(CarFactory.BENTLEY).show();
	}

	private static void testBuilder() {
		RobotDirector rd = new RobotDirector(new OldStyleRobotBuilder());
		rd.makeRobot();
		Robot robot = rd.getRobot();
		System.out.println("Builder\t" + robot.getArms());
		System.out.println("Builder\t" + robot.getHead());
		System.out.println("Builder\t" + robot.getLegs());
		System.out.println("Builder\t" + robot.getTorso());
	}

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

	private static void testAdapter() {
		MediaPlayer pc = new Computer();
		pc.play();
		MediaPlayer br = new Blu_ray_disc_player();
		br.play();
	};

	private static void testBridge() {
		BuyerSGuide bSGuide = new BuyerSGuide(new Hybids_N_Electric_Vehicle(), new Black());
		bSGuide.addToCart();
	};

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
	};

	private static void testFacade() {
		com.catherine.facade.CarFactory cFactory = new com.catherine.facade.CarFactory();
		cFactory.buildCoupe();
		cFactory.buildConvertible();
	}

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

	private static void testProxy() {
		ImageLoaderProxy imageLoader = new ImageLoaderProxy();
		imageLoader.display();
		imageLoader.display();
	}

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

	private static void testIterator() {
		Sequence sequence = new Sequence();
		System.out.print("Iterator: ");
		for (Iterator iterator = sequence.getIterator(); iterator.hasNext();)
			System.out.print(iterator.next() + " ");
		System.out.print("\n");
	}

	private static void testMediator() {
		User user = new User("A001", "Sev");
		user.sendMessage("Hi there.");
	}

	private static void testMemento() {
		Settings settings = new Settings();

		World village1 = new World();
		village1.setAmmo("Hunter arrow");
		village1.setWeapon("Sharpshot bow");
		village1.setXP(10000);
		village1.setOutfit("Noar survivor");
		settings.save(village1.getState());
		System.out.println(settings.load());

		World village2 = new World();
		village2.setAmmo("Fire arrow");
		village2.setWeapon("War bow");
		village2.setXP(20000);
		village2.setOutfit("Shield weaver");
		settings.save(village2.getState());
		System.out.println(settings.load());
	}

	private static void testObserver() {
		BroadcastManager manager = new BroadcastManager();
		new BinaryObserver(manager);
		new HexObserver(manager);
		new OctalObserver(manager);
		manager.setState(17);
		manager.setState(300);
	}

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

	private static void testState() {
		Gear g = new Gear1();
		System.out.println(g.getState());

		g = new Gear2();
		System.out.println(g.getState());

		g = new GearR();
		System.out.println(g.getState());
	}
}
