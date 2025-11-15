/**
 * @author Komal
 * @since 2025-10-20
 * @description Service implementation class that handles the business logic for user addresses.
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
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository repository;

    @Autowired
    public UserAddressServiceImpl(UserAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAddress saveAddress(UserAddress address) {
        return repository.save(address);
    }

    @Override
    public List<UserAddress> getAllAddresses() {
        return repository.findAll();
    }

    @Override
    public Optional<UserAddress> getAddressById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserAddress> getAddressesByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
