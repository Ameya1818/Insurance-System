/**@author Komal Gaikwad
 * @since 2025-10-20
 * @description Repository for performing database operations on user addresses.
*/
package com.insurancesystem.repository;

import com.insurancesystem.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	List<UserAddress> findByUserId(Long userId);
}
