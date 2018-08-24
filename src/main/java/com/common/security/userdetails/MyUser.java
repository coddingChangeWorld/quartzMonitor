package com.common.security.userdetails;

import com.common.tool.JacksonJson;
import java.io.PrintStream;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
	private UserDto userdto;

	public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, UserDto dto) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userdto = dto;
	}

	public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserDto getUserdto() {
		return this.userdto;
	}

	public void setUserdto(UserDto userdto) {
		this.userdto = userdto;
	}

	public String getJson() {
		System.out.println(JacksonJson.toJson(this.userdto));
		return JacksonJson.toJson(this.userdto);
	}
}
