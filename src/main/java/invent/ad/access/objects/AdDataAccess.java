package invent.ad.access.objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import invent.ad.objects.requests.AdHitRequest;
import invent.ad.objects.requests.AdRequest;
import invent.ad.objects.requests.OtpRequest;
import invent.ad.objects.responses.AdObject;
import invent.ad.storedprocs.AddMessageProc;
import invent.ad.storedprocs.GetAdProc;
import invent.ad.storedprocs.ProcessOtpProc;
import invent.ad.storedprocs.SaveHitProc;

@Component
public class AdDataAccess {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List getAd(AdRequest adRequest){
		GetAdProc adProc=new GetAdProc();
		adProc.setJdbcTemplate(jdbcTemplate);
		return adProc.getAds(adRequest);
	}
	
	public void saveHits(AdHitRequest adHitRequest){
		SaveHitProc hitProc=new SaveHitProc();
		hitProc.setJdbcTemplate(jdbcTemplate);
		hitProc.saveHit(adHitRequest);
		
	}
	
	public String proccessOtp(OtpRequest otpRequest, String otpOperation){
		ProcessOtpProc otpProc=new ProcessOtpProc();
		otpProc.setJdbcTemplate(jdbcTemplate);
		return otpProc.processOtp(otpRequest,otpOperation);
	}
	
	public void queueSmsMessage(String mobileNumber, String otpMessage){
		AddMessageProc addMessageProc=new AddMessageProc();
		addMessageProc.setJdbcTemplate(jdbcTemplate);
		addMessageProc.addMessage(mobileNumber, otpMessage);
	}
	

}
