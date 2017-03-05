package invent.ad.enumerations;

public enum AdType {

	VIDEO(1),
	AUDIO(2),
	IMAGE(3);
	
	private int adType;
	
	AdType(int adType){
		this.adType=adType;
	}

	public int getAdType() {
		return adType;
	}
	
	
}
