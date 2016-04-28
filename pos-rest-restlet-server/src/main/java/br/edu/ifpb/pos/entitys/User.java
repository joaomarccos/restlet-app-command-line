package br.edu.ifpb.pos.entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
@NamedQueries({
    @NamedQuery(name = "user.all",query = "SELECT u FROM User u")   
})
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String code, String name, String password) {
        this.code = code;
        this.name = name;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.code, other.code);
    }

}
