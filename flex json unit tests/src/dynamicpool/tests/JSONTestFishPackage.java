package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dynamicpool.bll.model.FishFactory;
import dynamicpool.bll.model.FishPackage;
import dynamicpool.bll.model.FishType;
import dynamicpool.bll.model.IFish;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestFishPackage {
	@Test
	public void Test() {
		IFish fish = createCycle();
		FishPackage fishPackage = new FishPackage();
		fishPackage.setFish(fish);
		fishPackage.setClientName("just a client name");
		
		String s = new JSONSerializer().serialize(fishPackage);
		System.out.println(s);
		
		FishPackage r = new JSONDeserializer<FishPackage>().deserialize(s);
		assertTrue(r.equals(fishPackage));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}
	private IFish createCycle() {
		IFish fish = FishFactory.createFishWithCycleTrajectory(40, 15, FishType.FISH2);
		fish.updateLocation(0.4345f);
		return fish;
	}
}
