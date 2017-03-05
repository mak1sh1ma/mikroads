package invent.ad.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import invent.ad.access.objects.AdDataAccess;
import invent.ad.objects.requests.AdHitRequest;
import invent.ad.objects.requests.AdRequest;
import invent.ad.objects.requests.OtpRequest;
import invent.ad.objects.responses.AdObject;
import invent.ad.objects.responses.OtpResponse;
import invent.ad.storedprocs.ProcessOtpProc;

@RestController
public class AdController {
	
	@Autowired
	AdDataAccess adAccess;
	
	
	@RequestMapping(value="/getAds", method=RequestMethod.POST, consumes="application/json")
	public List getAd(@Valid @RequestBody AdRequest adRequest, BindingResult bindingResult, HttpServletResponse response){
		setCrossOrigin(response);
		if(bindingResult.hasErrors()){
			return bindingResult.getFieldErrors();
		}
				
		return adAccess.getAd(adRequest);
	}
	
	
	@RequestMapping(value="/saveHits", method=RequestMethod.POST)
	public void saveAdHits(@Valid @RequestBody AdHitRequest adHitRequest, BindingResult bindingResult, HttpServletResponse response){
		setCrossOrigin(response);
		adAccess.saveHits(adHitRequest);
	}
	
	@RequestMapping(value="/processOtp", method=RequestMethod.POST)
	public OtpResponse generateOtp(@Valid @RequestBody OtpRequest otpRequest, BindingResult bindingResult,HttpServletResponse response){
		setCrossOrigin(response);
		OtpResponse otpResponse=new OtpResponse();
		if(otpRequest.getOtpOperation().equals("OTPGNRT")){
			String resultingOtp=adAccess.proccessOtp(otpRequest, otpRequest.getOtpOperation());
			String otpToSend="";
			if(resultingOtp==null){
				String secret = Base32.random();
				Totp totp = new Totp(secret);
				otpToSend=totp.now();
				otpResponse.setOtp(otpToSend);
				otpResponse.setOtpStatus("GENERATED");
				adAccess.queueSmsMessage(otpRequest.getMobileNumber(), otpToSend);
			}else{
				otpResponse.setOtp(resultingOtp);
				otpResponse.setOtpStatus("PRE-GENERATED");
				adAccess.queueSmsMessage(otpRequest.getMobileNumber(), resultingOtp);
			}
			
			
		}else if(otpRequest.getOtpOperation().equals("OTPVRFY")){
			String resultingOtp=adAccess.proccessOtp(otpRequest,otpRequest.getOtpOperation());
			if(resultingOtp==null){
				otpResponse.setOtp(otpRequest.getOtp());
				otpResponse.setOtpStatus("INVALID");
			}else{
				otpResponse.setOtp(resultingOtp);
				otpResponse.setOtpStatus("VALID");
			}
		}
		else{
			otpResponse.setOtpStatus("INVALID OPERATION");
		}
		
		return otpResponse;
	}
	
	@RequestMapping(value="/**", method=RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		setCrossOrigin(response);
	    System.out.println("preflight");
	} 
	
	private void setCrossOrigin(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	}
	
	
}
