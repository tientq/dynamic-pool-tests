package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishPackage;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestFishPackage {
	@Test
	public void Test() {
		Fish fish = createCycle();
		FishPackage fishPackage = new FishPackage();
		fishPackage.setFish(fish);
		fishPackage.setClientName("just a client name");
		
		String s = new JSONSerializer().serialize(fishPackage);
		System.out.println(s);
		
		FishPackage r = new JSONDeserializer<FishPackage>().deserialize(s);
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
		
		assertTrue(r.equals(fishPackage));
	}
	private Fish createCycle() {
		Fish fish = FishFactory.createFishWithCycleTrajectory(40, 15, FishType.FISH2);
		fish.updateLocation(0.4345f);
		return fish;
	}
}
