package com.catherine;

import java.util.ArrayList;
import java.util.List;

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
import com.catherine.factory.ColorFactory;
import com.catherine.filter.AndCriteria;
import com.catherine.filter.OrCriteria;
import com.catherine.filter.Person;
import com.catherine.filter.criteria.Criteria;
import com.catherine.filter.criteria.CriteriaFemale;
import com.catherine.filter.criteria.CriteriaMale;
import com.catherine.filter.criteria.CriteriaMarried;
import com.catherine.filter.criteria.CriteriaSingle;
import com.catherine.prototype.Color;
import com.catherine.prototype.ColorCache;
import com.catherine.prototype.Type;
import com.catherine.singleton.BillPughSingleton;
import com.catherine.singleton.EagerInitializingSingleton;
import com.catherine.singleton.EnumSingleton;
import com.catherine.singleton.LazyInitializingSingleton;
import com.catherine.singleton.SafeLazyInitializingSingleton;

public class Main {

	public static void main(String[] args) {
		// testSingleton();
		// testFactory();
		// testAbstractFactory();
		// testBuilder();
		// testPrototype();
		// testAdapter();
		// testBridge();
		testFilter();
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
}
