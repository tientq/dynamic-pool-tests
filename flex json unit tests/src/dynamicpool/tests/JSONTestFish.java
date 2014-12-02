package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import dynamicpool.bll.model.Fish;
import dynamicpool.bll.model.FishFactory;
import dynamicpool.bll.model.FishType;
import dynamicpool.bll.model.IFish;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestFish {

	@Before
	public void setUp() throws Exception {
		loadLog4j();
	}
	public void loadLog4j() {
		String log4JPropertyFile = "conf/log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
		} catch (IOException e) {
			System.out.println("Opps, cannot load log4j.properties");
		}
	}

	@Test
	public void testLine() {
		IFish fish = createLine();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		IFish r = new JSONDeserializer<IFish>().deserialize(s);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);
//		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testCycle() {
		IFish fish = createCycle();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		IFish r = new JSONDeserializer<IFish>().deserialize(s);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);
//		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testSin() {
		IFish fish = createSin();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		IFish r = new JSONDeserializer<IFish>().deserialize(s);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);
//		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void TestList() {
		List<IFish> arrayList = new ArrayList<IFish>();
		IFish f1 = createLine();
		IFish f2 = createCycle();
		IFish f3 = createSin();
		IFish f4 = createSin();
		IFish f5 = createLine();
		arrayList.add(f1);
		arrayList.add(f2);
		arrayList.add(f3);
		arrayList.add(f4);
		arrayList.add(f5);
		
		String s = new JSONSerializer().serialize(arrayList);
		System.err.println(s);
		
		List<IFish> r = new JSONDeserializer<List<IFish>>().deserialize(s);
		assertTrue(r instanceof ArrayList);
		assertTrue(r.size() == arrayList.size());
		assertTrue(Fish.equals(f1, r.get(0)));
		assertTrue(Fish.equals(f2, r.get(1)));
		assertTrue(Fish.equals(f3, r.get(2)));
		assertTrue(Fish.equals(f4, r.get(3)));
		assertTrue(Fish.equals(f5, r.get(4)));
		
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}

	private IFish createLine() {
		IFish fish = FishFactory.createFishWithLineTrajectory(100, 51, FishType.FISH1);
		fish.updateLocation(0.235f);
		return fish;
	}

	private IFish createCycle() {
		IFish fish = FishFactory.createFishWithCycleTrajectory(40, 15, FishType.FISH2);
		fish.updateLocation(0.4345f);
		return fish;
	}

	private IFish createSin() {
		IFish fish = FishFactory.createFishWithSinTrajectory(45, 20, FishType.FISH3);
		fish.updateLocation(6.653f);
		return fish;
	}

}
