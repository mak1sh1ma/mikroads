package invent.ad.storedprocs;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import invent.ad.objects.requests.OtpRequest;

public class ProcessOtpProc extends StoredProcedure{

JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public ProcessOtpProc(){
		super();
	}
	
	public String processOtp(OtpRequest otpRequest, String otpOperation){
		super.setJdbcTemplate(jdbcTemplate);
		setSql("PROCESS_OTP");
		declareParameter(new SqlParameter("mobileNumber", Types.VARCHAR));
		declareParameter(new SqlParameter("otpToValidate", Types.VARCHAR));
		declareParameter(new SqlParameter("otpOperation", Types.VARCHAR));
		declareParameter(new SqlOutParameter("otpResult", Types.VARCHAR));
		compile();
		Map resultMap=execute(otpRequest.getMobileNumber(), otpRequest.getOtp(), otpOperation);
		return (String)resultMap.get("otpResult");		
	}

}
