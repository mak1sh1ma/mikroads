package invent.ad.objects.responses;

public class AdObject {
	
	
	private String adUrl;
	private String adLink;
	private int adType;
	
	public String getAdUrl(){
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	
	public String getAdLink(){
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	
	public int getAdType() {
		return adType;
	}
	public void setAdType(int adType) {
		this.adType = adType;
	}
}
