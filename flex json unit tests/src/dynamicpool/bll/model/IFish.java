package dynamicpool.bll.model;

public interface IFish {
	String getFishId();

	void setFishId(String fishId);

	Boundary getBoundary();

	void setBoundary(Boundary boundary);

	Trajectory getTrajectory();
 
	void setTrajectory(Trajectory trajectory);

	FishType getFishType();

	FishState getFishState();

	void setFishState(FishState fishState);

	void updateLocation(float deltaTime);

	IFish clone();
}