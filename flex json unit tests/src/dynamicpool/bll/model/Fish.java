package dynamicpool.bll.model;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flexjson.JSON;

public class Fish implements IFish {
	private String fishId = UUID.randomUUID().toString();
	private FishType fishType = FishType.FISH1;
	private Trajectory trajectory = new NoneTrajectory();
	private Boundary boundary = new Boundary();
	private FishState fishState = FishState.INSIDE;
	@JSON(include = false)
	private Logger logger = LoggerFactory.getLogger(Fish.class);

	public Fish() {

	}

	public Fish(FishType fishType, Trajectory fishTrajectory,
			Boundary fishBoundary) {
		this.fishType = fishType;
		this.trajectory = fishTrajectory;
		this.boundary = fishBoundary;
	}

	@Override
	public String getFishId() {
		return fishId;
	}

	@Override
	public void setFishId(String fishId) {
		this.fishId = fishId;
	}

	@Override
	public Boundary getBoundary() {
		return boundary;
	}

	@Override
	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	@Override
	public Trajectory getTrajectory() {
		if (trajectory == null)
			return new NoneTrajectory();
		return trajectory;
	}

	@Override
	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public FishType getFishType() {
		return fishType;
	}

	public void setFishType(FishType fishType) {
		this.fishType = fishType;
	}

	@Override
	public FishState getFishState() {
		return fishState;
	}

	@Override
	public void setFishState(FishState fishState) {
		logger.debug("Fish {} change state from {} to {}", this.getFishId(),
				this.fishState, fishState);
		this.fishState = fishState;
	}

	@Override
	public void updateLocation(float deltaTime) {
		Point updateLocation = getTrajectory().updateLocation(deltaTime);
		boundary.getLocation().setLocation(updateLocation.getX(),
				updateLocation.getY());
	}

	public IFish clone() {
		FishType fishType = this.fishType;
		Trajectory trajectory = this.getTrajectory().clone();
		Boundary boundary = this.getBoundary().clone();
		IFish fish = new Fish(fishType, trajectory, boundary);
		fish.setFishId(fishId);
		fish.setFishState(fishState);
		return fish;
	}

	private boolean equals(Fish f) {
		return this.fishId.equals(f.getFishId())
				&& this.fishType == f.getFishType()
				&& this.fishState == f.getFishState()
				&& this.trajectory.equals(f.getTrajectory())
				&& this.boundary.equals(f.getBoundary());
	}

	public static boolean equals(IFish f1, IFish f2) {
		if (f1 instanceof Fish && f2 instanceof Fish) {
			return ((Fish)f1).equals((Fish)f2);
		}
		return false;
	}
}
