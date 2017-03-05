package invent.ad.objects.requests;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AdRequest implements Serializable{

		@Min(1)
		private int adType;
		
		@NotNull
		@NotEmpty
		@Size(min=12, max=12)
		private String adTerminalMac;
		public int getAdType() {
			return adType;
		}
		public void setAdType(int adType) {
			this.adType = adType;
		}
		public String getAdTerminalMac() {
			return adTerminalMac;
		}
		public void setAdTerminalMac(String adTerminalMac) {
			this.adTerminalMac = adTerminalMac;
		}
		
		
}
