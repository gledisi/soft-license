package city.ac.security.util;



import city.ac.security.entity.RoleEntity;
import city.ac.security.entity.UserEntity;
import city.ac.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class LicenseDetailService implements UserDetailsService {

	@Lazy
	@Autowired
	private UserService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity user = accountService.findWithUsername(username);
		return new LicenseUserDetail.Builder()
				.withUsername(user.getUsername())
				.withPassword(user.getPassword())
				.withAuthorities(toTrackListAuthority(user.getRole()))
				.withValid(!user.getDeleted())
				.build();
	}

	  private LicenseAuthority toTrackListAuthority(RoleEntity role) {
		    return new LicenseAuthority(role.getId(), role.getType().getValue());
		  }
}
