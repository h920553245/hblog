package cn.hergua.hblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Entity
@Table(name = "tab_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long roleId;

    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}