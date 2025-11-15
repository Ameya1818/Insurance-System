package com.insurancesystem.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
<<<<<<< HEAD
=======
 * Entity representing user roles such as ADMIN, USER, or UNDERWRITER. Includes
 * role name, description, and user mapping.
 *
>>>>>>> aeb0ebb9f66718fd665201b34385c680e03dece0
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description This class handles Role entities.
 */
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = "roleName") })

public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@NotBlank(message = "Role name is required")
	@Size(min = 3, max = 20, message = "Role name must be between 3 and 20 characters")
	@Column(nullable = false, unique = true)
	private String roleName;

	@NotBlank(message = "Description cannot be empty")
	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserRegistration> userList;

	public Role() {
	}

	public Role(Long roleId, String roleName, String description) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserRegistration> getUserList() {
		return userList;
	}

	public void setUserList(List<UserRegistration> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description + "]";
	}
}
