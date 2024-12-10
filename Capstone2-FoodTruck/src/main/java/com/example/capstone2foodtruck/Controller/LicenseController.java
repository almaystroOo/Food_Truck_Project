package com.example.capstone2foodtruck.Controller;


import com.example.capstone2foodtruck.Model.Truck_License;
import com.example.capstone2foodtruck.Service.LicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/License")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(licenseService.getLicense());
    }

    @PostMapping("/add/{foodTruckId}")
    public ResponseEntity<?> add(@RequestBody @Valid Truck_License truckLicense, @PathVariable Integer foodTruckId, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        licenseService.addLicense(truckLicense, foodTruckId);
        return ResponseEntity.status(HttpStatus.OK).body("License added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Truck_License truckLicense, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        licenseService.updateLicense(id, truckLicense);
        return ResponseEntity.status(HttpStatus.OK).body("License updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        licenseService.deleteLicense(id);
        return ResponseEntity.status(HttpStatus.OK).body("License deleted");
    }

    @GetMapping("/expiration-period/{foodTruckId}")
    public ResponseEntity<Integer> calculateExpirationPeriod(@PathVariable Integer foodTruckId) {
        Integer daysUntilExpiration = licenseService.calculateExpirationPeriod(foodTruckId);
        return ResponseEntity.ok(daysUntilExpiration);
    }
}

//public class LicenseControoler {
//
//    private final LicenseService licenseService;
//
//    @GetMapping("/get")
//    public ResponseEntity get() {
//
//        return ResponseEntity.status(HttpStatus.OK).body(licenseService.getLicense());
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity add(@RequestBody @Valid License license, Errors errors) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//
//        licenseService.addLicense(license);
//        return ResponseEntity.status(HttpStatus.OK).body("License added");
//    }
//
//
//       @PutMapping("/update/{id}")
//    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid License license, Errors errors) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//        licenseService.updateLicense(id, license);
//        return ResponseEntity.status(HttpStatus.OK).body("License update");
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//
//        licenseService.deleteLicense(id);
//        return ResponseEntity.status(HttpStatus.OK).body("License delete");
//    }
//
//    @GetMapping("/expiration-period/{foodTruckId}")
//    public ResponseEntity<Integer> calculateExpirationPeriod(@PathVariable Integer foodTruckId) {
//        Integer daysUntilExpiration = licenseService.calculateExpirationPeriod(foodTruckId);
//        return ResponseEntity.ok(daysUntilExpiration);
//    }
//
//}
