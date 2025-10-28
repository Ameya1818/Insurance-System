package com.insurancesystem.restcontroller;



import com.insurancesystem.service.CancellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class that handles API requests related to policy cancellations.
 * Provides an endpoint to cancel a purchased insurance policy based on its ID.
 *
 * 
 * - Receive HTTP POST requests for policy cancellation.
 * - Delegate cancellation logic to the CancellationService.
 * - Return appropriate responses to the client.
 *
 * Author: Ameya Shingane
 * Version: 1.0
 */
@RestController
@RequestMapping("/api/purchases")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;
   
    /**
     * Cancels a policy based on the given purchase ID.
     *
     * @param id the unique identifier of the policy purchase
     * @return a response message indicating the result of the cancellation
     */

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelPolicy(@PathVariable Long id) {
        String response = cancellationService.cancelPolicy(id);
        return ResponseEntity.ok(response);
    }
}
