package uk.co.shrewsburyanglers.membership.model;

import org.springframework.security.core.GrantedAuthority;
import java.util.Base64;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * d8888
 * d88888
 * d88P888
 * d88P 888 888  888  .d88b.  888  888
 * d88P  888 888  888 d88""88b `Y8bd8P'
 * d88P   888 Y88  88P 888  888   X88K
 * d8888888888  Y8bd8P  Y88..88P .d8""8b.
 * d88P     888   Y88P    "Y88P"  888  888
 *
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 22/09/2016
 *         Time : 16:03
 */
@Entity
@Table(name = "user", schema = "", catalog = "membership")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "USERNAME")
    private String username;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    private YNEnum active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", catalog = "membership", schema = "",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false))
    private Set<RoleEntity> roles;

    public UserEntity() {}

    /**
     *
     * @param username
     * @param password
     * @param email
     */
    public UserEntity(final String username, final byte[] password, final String email) {
        this.username = username;
        setPasswordHash(password);
        this.email = email;
    }




    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setActive(YNEnum active) {
        this.active = active;
    }

    public boolean isAccountNonExpired() {
        return isActive();
    }

    public boolean isAccountNonLocked() {
        return isActive();
    }

    public boolean isCredentialsNonExpired() {
        return isActive();
    }

    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public boolean isActive() {
        return active == YNEnum.Y;
    }

    /**
     * <p>
     *     Set the password as a {@link Base64} encoded String of the
     *     given byte[] hash.
     * </p>
     * @param passwordHash
     */
    public void setPasswordHash(byte[] passwordHash) {
        byte[] base64Hash = Base64.getEncoder().encode(passwordHash);
        password = new String(base64Hash);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("########\n");
        buffer.append("ID - ").append(id).append("\n");
        buffer.append("username - ").append(username).append("\n");
        buffer.append("email - ").append(email).append("\n");
        buffer.append("active - ").append(active).append("\n");

        for(RoleEntity role : roles) {
            buffer.append("role - ").append(role.getName()).append("\n");
        }

        return buffer.toString();
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
