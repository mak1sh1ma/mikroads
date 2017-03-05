package invent.ad.storedprocs;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import invent.ad.objects.requests.AdHitRequest;
import invent.ad.objects.requests.AdRequest;
import invent.ad.objects.responses.AdObject;
import invent.ad.proc.mappers.GetAdProcMapper;

public class SaveHitProc extends StoredProcedure{

	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SaveHitProc(){
		super();
	}
	
	public void saveHit(AdHitRequest adHitRequest){
		String baseString="INSERT INTO ad_hits(ad_id, ad_terminal_id, hit_time, viewer_mac) VALUES ";
		String valuesBase="(%d,[ad_terminal_id], %s, '%s'),";
		Map adsList=null;
		super.setJdbcTemplate(jdbcTemplate);
		setSql("SAVE_HITS");
		for(int adId:adHitRequest.getAdIds()){
			baseString=baseString+String.format(valuesBase, adId,"NOW()" ,adHitRequest.getMobileDeviceMac());
		}
		
		baseString=baseString.concat(";").replace("),;", ");");
		declareParameter(new SqlParameter("adTerminalMac", Types.VARCHAR));
		declareParameter(new SqlParameter("queryText", Types.VARCHAR));
		compile();
		adsList=execute(adHitRequest.getAdTerminalMac(), baseString);
	
	}
}
