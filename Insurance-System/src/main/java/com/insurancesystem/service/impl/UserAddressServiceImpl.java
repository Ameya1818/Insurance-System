/**@author Komal Gaikwad
  *@since 2025-10-20
  *@description Service implementation class that handles the business logic for user addresses.
*/
package com.insurancesystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.UserAddress;
import com.insurancesystem.repository.UserAddressRepository;
import com.insurancesystem.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService{

	@Autowired
	private final UserAddressRepository repository;

    public UserAddressServiceImpl(UserAddressRepository repository) {
        this.repository = repository;
    }

    public UserAddress saveAddress(UserAddress address) {
        return repository.save(address);
    }

    public List<UserAddress> getAllAddresses() {
        return repository.findAll();
    }

    public Optional<UserAddress> getAddressById(Long id) {
        return repository.findById(id);
    }

    public List<UserAddress> getAddressesByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }

}
