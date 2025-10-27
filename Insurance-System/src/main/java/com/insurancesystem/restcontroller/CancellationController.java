package com.insurancesystem.restcontroller;



import com.insurancesystem.service.CancellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelPolicy(@PathVariable Long id) {
        String response = cancellationService.cancelPolicy(id);
        return ResponseEntity.ok(response);
    }
}
