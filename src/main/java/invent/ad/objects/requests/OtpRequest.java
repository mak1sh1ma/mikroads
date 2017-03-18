package invent.ad.objects.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class OtpRequest {

		@NotNull
		@NotEmpty
		@Size(min=11, max=13)
		private String mobileNumber;
		
		private String otp;
		
		@NotNull
		@NotEmpty
		//OTPGNRT, OTPVRFY
		private String otpOperation;

		@NotEmpty
		private String birthDay;
		
		@NotEmpty
		private String name;
		
		private String deviceMac;
		
		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public String getOtpOperation() {
			return otpOperation;
		}

		public void setOtpOperation(String otpOperation) {
			this.otpOperation = otpOperation;
		}

		public String getBirthDay() {
			return birthDay;
		}

		public void setBirthDay(String birthDay) {
			this.birthDay = birthDay;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDeviceMac() {
			return deviceMac;
		}

		public void setDeviceMac(String deviceMac) {
			this.deviceMac = deviceMac;
		}
		
}
