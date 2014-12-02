package dynamicpool.bll.model;

public class FishPackage {
	private String clientName;
	private IFish fish;

	public FishPackage() {

	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public IFish getFish() {
		return fish;
	}

	public void setFish(IFish fish) {
		this.fish = fish;
	}

	public boolean equals(FishPackage f) {
		return ((clientName == null && f.clientName == null) || (this.clientName != null
				&& f.clientName != null && this.clientName.equals(f.clientName)))
				&& Fish.equals(this.fish, f.fish);
	}
}
