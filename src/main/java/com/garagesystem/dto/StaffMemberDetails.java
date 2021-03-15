package com.garagesystem.dto;

import java.util.Collection;

import com.garagesystem.model.Staff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StaffMemberDetails implements UserDetails {

    private Staff staff;

    public StaffMemberDetails(Staff user) {
        this.staff = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
        return staff.getUserName();
    }

    public long getId() {
        return staff.getId();
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
        return true;
    }

    public String getFullName() {
        return staff.getFirstName() + " " + staff.getLastName();
    }

}