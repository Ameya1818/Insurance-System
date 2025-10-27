/**@author Komal Gaikwad
  *@since 2025-10-20
  *@description Service interface for defining business operations related to user addresses.
*/
package com.insurancesystem.service;

import com.insurancesystem.entity.UserAddress;
import java.util.List;
import java.util.Optional;


public interface UserAddressService {


    public UserAddress saveAddress(UserAddress address);

    public List<UserAddress> getAllAddresses(); 

    public Optional<UserAddress> getAddressById(Long id); 

    public List<UserAddress> getAddressesByUserId(Long userId); 

    public void deleteAddress(Long id);
}
