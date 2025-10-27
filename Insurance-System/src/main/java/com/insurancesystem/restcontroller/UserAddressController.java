/**@author Komal Gaikwad
 * @since 2025-10-20
 * @description Controller for handling API requests related to user addresses.

 */
package com.insurancesystem.restcontroller;

import com.insurancesystem.entity.UserAddress;

import com.insurancesystem.service.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAddressController {

    private final UserAddressService service;

    public UserAddressController(UserAddressService service) {
        this.service = service;
    }

    @PostMapping("/saveAddress")
    public ResponseEntity<UserAddress> createAddress(@Valid @RequestBody UserAddress address) {
        return ResponseEntity.ok(service.saveAddress(address));
    }

    @GetMapping("/getAll")
    public List<UserAddress> getAllAddresses() {
        return service.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getAddressById(@PathVariable Long id) {
        return service.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserAddress> getAddressesByUserId(@PathVariable Long userId) {
        return service.getAddressesByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddress> updateAddress(@PathVariable Long id, @Valid @RequestBody UserAddress address) {
        return service.getAddressById(id).map(existing -> {
            address.setAddressId(id);
            return ResponseEntity.ok(service.saveAddress(address));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
