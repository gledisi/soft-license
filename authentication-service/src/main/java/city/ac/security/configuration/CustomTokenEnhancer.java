package city.ac.security.configuration;

import city.ac.security.dto.Role;
import city.ac.security.util.JwtClaim;
import city.ac.security.util.LicenseAuthority;
import city.ac.security.util.LicenseUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.*;
import java.util.stream.Collectors;


public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        Map<String, Object> info = new HashMap<>();
        LicenseUserDetail userDetail = (LicenseUserDetail) oAuth2Authentication.getPrincipal();
        info.put(JwtClaim.ROLE,toRole(userDetail.getAuthorities()));
        info.put(JwtClaim.USERNAME,userDetail.getUsername());
        token.setAdditionalInformation(info);
        return token;
    }

    private List<Role> toRole(Collection<? extends GrantedAuthority> authorities){
        return authorities.stream().
                map(p -> new Role(((LicenseAuthority) p).getId(), p.getAuthority()))
                .collect(Collectors.toList());
    }
}
