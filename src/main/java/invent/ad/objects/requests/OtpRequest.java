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
		
}
