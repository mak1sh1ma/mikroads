package invent.ad.storedprocs;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class AddMessageProc extends StoredProcedure{
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addMessage(String mobileNumber, String otpMessage, String name, String birthDay, String deviceMac, String otpGenerateType){
		super.setJdbcTemplate(jdbcTemplate);
		setSql("QUEUE_MESSAGE");
		declareParameter(new SqlParameter("mobileNumber", Types.VARCHAR));
		declareParameter(new SqlParameter("otpMessage", Types.VARCHAR));
		declareParameter(new SqlParameter("birthDay", Types.VARCHAR));
		declareParameter(new SqlParameter("name", Types.VARCHAR));
		declareParameter(new SqlParameter("otpGenerateType", Types.VARCHAR));
		declareParameter(new SqlParameter("deviceMac", Types.VARCHAR));
		compile();
		execute(mobileNumber, otpMessage, name, birthDay, deviceMac, otpGenerateType);
	}
	public AddMessageProc(){
		super();
	}
	
}
