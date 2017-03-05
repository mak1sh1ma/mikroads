package invent.ad.proc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import invent.ad.objects.responses.AdObject;


public class GetAdProcMapper implements RowMapper<AdObject>{

	
	public AdObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdObject adObject=new AdObject();
		adObject.setAdUrl(rs.getString("ad_path"));
		adObject.setAdLink(rs.getString("ad_link"));
		adObject.setAdType(rs.getInt("ad_type_id"));
		return adObject;
	}

	

}
