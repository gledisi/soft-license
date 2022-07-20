package city.ac.security.util;



import city.ac.security.dto.RtsAuthority;
import city.ac.security.entity.RoleEntity;
import city.ac.security.entity.UserEntity;
import city.ac.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class RtsDetailService implements UserDetailsService {

	@Lazy
	@Autowired
	private UserService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity user = accountService.findWithUsername(username);
		return new RtsUserDetail.Builder()
				.withUsername(user.getUsername())
				.withPassword(user.getPassword())
				.withAuthorities(toTrackListAuthority(user.getRole()))
				.withValid(!user.getDeleted())
				.build();
	}

	  private RtsAuthority toTrackListAuthority(RoleEntity role) {
		    return new RtsAuthority(role.getId(), role.getType().getValue());
		  }
}
