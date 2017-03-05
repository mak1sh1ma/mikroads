package invent.ad.objects.requests;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AdHitRequest {

	@NotNull
	@NotEmpty
	@Size(min=12, max=12)
	private String adTerminalMac;
	
	@NotNull
	@NotEmpty
	@Size(min=12, max=12)
	private String mobileDeviceMac;
	
	//@NotNull
	//@NotEmpty
	//@Size(min=13, max=13)
	private String mobileNumber;
	
	@NotNull
	@NotEmpty
	private List<Integer> adIds;
	
	
	public String getAdTerminalMac() {
		return adTerminalMac;
	}
	public void setAdTerminalMac(String adTerminalMac) {
		this.adTerminalMac = adTerminalMac;
	}
	public String getMobileDeviceMac() {
		return mobileDeviceMac;
	}
	public void setMobileDeviceMac(String mobileDeviceMac) {
		this.mobileDeviceMac = mobileDeviceMac;
	}
	public List<Integer> getAdIds() {
		return adIds;
	}
	public void setAdIds(List<Integer> adIds) {
		this.adIds = adIds;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
