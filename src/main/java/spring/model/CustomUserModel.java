package spring.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.domain.User;

import java.util.Collection;
import java.util.List;

public class CustomUserModel extends User implements UserDetails {
    private static final long serialVersionUID = 1L;
    private List<GrantedAuthority> userRolesString;

    public CustomUserModel(User user, List<GrantedAuthority> userRoles){
        super(user);
        this.userRolesString = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRolesString;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
