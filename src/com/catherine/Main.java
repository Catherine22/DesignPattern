package com.catherine;

import java.util.ArrayList;
import java.util.List;

import com.catherine.abstract_factory.CarFactory;
import com.catherine.adapter.Blu_ray_disc_player;
import com.catherine.adapter.Computer;
import com.catherine.adapter.MediaPlayer;
import com.catherine.bridge.Black;
import com.catherine.bridge.BuyerSGuide;
import com.catherine.bridge.Hybids_N_Electric_Vehicle;
import com.catherine.builder.OldStyleRobotBuilder;
import com.catherine.builder.Robot;
import com.catherine.builder.RobotDirector;
import com.catherine.business_delegate.BusinessDelegate;
import com.catherine.business_delegate.Client;
import com.catherine.business_delegate.ServiceType;
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
import com.catherine.composite_entity.Cashier;
import com.catherine.data_access_object.BlacklistDAOImpl;
import com.catherine.data_access_object.Contact;
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
import com.catherine.flyweight.ShapeFactory;
import com.catherine.front_controller.FrontController;
import com.catherine.intercepting_filter.Country;
import com.catherine.intercepting_filter.CountryFilter;
import com.catherine.intercepting_filter.DebuggerFilter;
import com.catherine.intercepting_filter.FilterManager;
import com.catherine.intercepting_filter.LevelFilter;
import com.catherine.intercepting_filter.MusicPlayer;
import com.catherine.intercepting_filter.member.Level;
import com.catherine.intercepting_filter.member.MemberInfo;
import com.catherine.interpreter.Expression;
import com.catherine.interpreter.Toolkits;
import com.catherine.iterator.Iterator;
import com.catherine.iterator.Sequence;
import com.catherine.mediator.User;
import com.catherine.memento.Settings;
import com.catherine.memento.World;
import com.catherine.mvc.RetrieveCouponFromDB;
import com.catherine.null_object.IDChecker;
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
import com.catherine.singleton.SynchronizedSample;
import com.catherine.state.Gear;
import com.catherine.state.Gear1;
import com.catherine.state.Gear2;
import com.catherine.state.GearR;
import com.catherine.strategy.Calculator;
import com.catherine.strategy.OperationAdd;
import com.catherine.strategy.OperationMultiply;
import com.catherine.strategy.OperationSubstract;
import com.catherine.template.Blizzard;
import com.catherine.template.Supercell;
import com.catherine.visitor.AccessLevel;
import com.catherine.visitor.PrivateLevel;
import com.catherine.visitor.ProtectedLevel;
import com.catherine.visitor.PublicLevel;
import com.catherine.visitor.RetrieveMethod;

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
		// testState();
		// testNullObject();
		// testStrategy();
		// testTemplate();
		// testVisitor();
		// testMVC();
		// testBusinessDelegate();
		// testCompositeEntity();
		// testDAO();
		// testSynchronized();
		// testFrontController();
		testInterceptingFilter();
	}

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

	private static void testFrontController() {
		FrontController fController = new FrontController();
		String token = null;
		fController.dispatchView(token);

		token = fController.inputCaptcha("XI0dk3");
		fController.dispatchView(token);

		token = fController.login("Oleg1234", "pw1234");
		fController.dispatchView(token);
	}

	private static void testSynchronized() {
		SynchronizedSample ss = new SynchronizedSample();
		ss.testStaticParamsAsUsual();
		ss.testStaticParams();
		ss.testStaticParamsFromDifferentObj();
		ss.testStaticMethod();
		ss.testStaticMethodAndSyncCodes();
	}

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

	private static void testCompositeEntity() {
		Cashier cashier = new Cashier();
		cashier.chectOut("white", "roast beef", "BBQ");
		cashier.printReceipt();

		cashier.chectOut("honey oat", "meatball", "olive oil & salt");
		cashier.printReceipt();
	}

	private static void testBusinessDelegate() {
		BusinessDelegate bd = new BusinessDelegate(ServiceType.EJB);
		Client client = new Client(bd);
		client.createTask();

		bd = new BusinessDelegate(ServiceType.JMS);
		client = new Client(bd);
		client.createTask();
	}

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

	private static void testTemplate() {
		Supercell supercell = new Supercell();
		supercell.buyAds();

		System.out.println("");

		Blizzard blizzard = new Blizzard();
		blizzard.buyAds();
	}

	private static void testStrategy() {
		Calculator calculator = new Calculator(new OperationAdd());
		System.out.println("4 + 5 = " + calculator.execute(4, 5));

		calculator = new Calculator(new OperationSubstract());
		System.out.println("4 - 5 = " + calculator.execute(4, 5));

		calculator = new Calculator(new OperationMultiply());
		System.out.println("4 * 5 = " + calculator.execute(4, 5));
	}

	private static void testNullObject() {
		IDChecker idChecker = new IDChecker();
		System.out.println(idChecker.getName("12351"));
		System.out.println(idChecker.getName("10000"));
		System.out.println(idChecker.getName("62343"));
		System.out.println(idChecker.getName("20000"));
		System.out.println(idChecker.getName("34261"));

	}

	private static void testState() {
		Gear g = new Gear1();
		System.out.println(g.getState());

		g = new Gear2();
		System.out.println(g.getState());

		g = new GearR();
		System.out.println(g.getState());
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

	private static void testObserver() {
		BroadcastManager manager = new BroadcastManager();
		new BinaryObserver(manager);
		new HexObserver(manager);
		new OctalObserver(manager);
		manager.setState(17);
		manager.setState(300);
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

	private static void testMediator() {
		User user = new User("A001", "Sev");
		user.sendMessage("Hi there.");
	}

	private static void testIterator() {
		Sequence sequence = new Sequence();
		System.out.print("Iterator: ");
		for (Iterator iterator = sequence.getIterator(); iterator.hasNext();)
			System.out.print(iterator.next() + " ");
		System.out.print("\n");
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

	private static void testProxy() {
		ImageLoaderProxy imageLoader = new ImageLoaderProxy();
		imageLoader.display();
		imageLoader.display();
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

	private static void testFacade() {
		com.catherine.facade.CarFactory cFactory = new com.catherine.facade.CarFactory();
		cFactory.buildCoupe();
		cFactory.buildConvertible();
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

	private static void testBridge() {
		BuyerSGuide bSGuide = new BuyerSGuide(new Hybids_N_Electric_Vehicle(), new Black());
		bSGuide.addToCart();
	};

	private static void testAdapter() {
		MediaPlayer pc = new Computer();
		pc.play();
		MediaPlayer br = new Blu_ray_disc_player();
		br.play();
	};

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

	private static void testBuilder() {
		RobotDirector rd = new RobotDirector(new OldStyleRobotBuilder());
		rd.makeRobot();
		Robot robot = rd.getRobot();
		System.out.println("Builder\t" + robot.getArms());
		System.out.println("Builder\t" + robot.getHead());
		System.out.println("Builder\t" + robot.getLegs());
		System.out.println("Builder\t" + robot.getTorso());
	}

	private static void testAbstractFactory() {
		CarFactory cf = new CarFactory();
		cf.getColor(CarFactory.RED).onDraw();
		cf.getBrand(CarFactory.BENTLEY).show();
	}

	private static void testFactory() {
		ColorFactory cf = new ColorFactory();
		cf.getColor(ColorFactory.BLUE).onDraw();
		cf.getColor(ColorFactory.RED).onDraw();
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

}
