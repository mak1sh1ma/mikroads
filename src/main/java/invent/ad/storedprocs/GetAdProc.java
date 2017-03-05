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

import invent.ad.objects.requests.AdRequest;
import invent.ad.objects.responses.AdObject;
import invent.ad.proc.mappers.GetAdProcMapper;

public class GetAdProc extends StoredProcedure{

	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GetAdProc(){
		super();
	}
	
	public List getAds(AdRequest adRequest){
		RowMapper adProcMapper=new GetAdProcMapper();
		Map adsList=null;
		super.setJdbcTemplate(jdbcTemplate);
		setSql("FETCH_AD");
		declareParameter(new SqlParameter("adType", Types.INTEGER));
		declareParameter(new SqlParameter("adTerminalMac", Types.VARCHAR));
		compile();
		adsList=execute(adRequest.getAdType(), adRequest.getAdTerminalMac(), adProcMapper);
		return (List)adsList.get("#result-set-1");
		
	}
}
