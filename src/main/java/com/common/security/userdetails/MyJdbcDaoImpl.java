package com.common.security.userdetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class MyJdbcDaoImpl extends JdbcDaoImpl {
	
	@SuppressWarnings("unchecked")
	protected List<UserDetails> loadUsersByUsername(String username)
	  {
	    String usersByUsernameQuery = getUsersByUsernameQuery();
	    return getJdbcTemplate().query(usersByUsernameQuery, new String[] { username }, new RowMapper()
	    {
	      public UserDetails mapRow(ResultSet rs, int rowNum)
	        throws SQLException
	      {
	        String username = rs.getString(1);
	        String password = rs.getString(2);
	        boolean enabled = rs.getBoolean(3);
	        
	        String userPhoto = rs.getString(4);
	        Double currency = Double.valueOf(rs.getDouble(5));
	        UserDto dto = new UserDto(userPhoto, currency, username);
	        MyUser user = new MyUser(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES, dto);
	        return user;
	      }
	    });
	  }

	protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();
		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}
		MyUser user = (MyUser) userFromUserQuery;
		return new MyUser(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities, user.getUserdto());
	}
}
