package com.catherine;

import com.catherine.abstract_factory.CarFactory;
import com.catherine.adapter.Cable;
import com.catherine.adapter.Computer;
import com.catherine.adapter.MediaPlayer;
import com.catherine.builder.OldStyleRobotBuilder;
import com.catherine.builder.Robot;
import com.catherine.builder.RobotDirector;
import com.catherine.factory.ColorFactory;
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
		testAdapter();
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
		pc.play(Cable.HDMI);
		pc.play(Cable.VGA);
	};
}
