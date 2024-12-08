package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.License;
import com.example.capstone2foodtruck.Repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;


    public List<License> getLicense(){
        return licenseRepository.findAll();
    }


    public void addLicense(License license){
        licenseRepository.save(license);
    }


    public void updateLicense(Integer id,License license){
        License oldLicense=licenseRepository.findLicenseById(id);

        if(oldLicense==null){
            throw new ApiExcepiton("id not found");
        }

        oldLicense.setFoodTruckId(license.getFoodTruckId());
        oldLicense.setIssuedDate(license.getIssuedDate());
        oldLicense.setExpiryDate(license.getExpiryDate());
        oldLicense.setStatus(license.getStatus());
        oldLicense.setFee(license.getFee());
        licenseRepository.save(oldLicense);
    }


    public void deleteLicense(Integer id){

        License oldLicense=licenseRepository.findLicenseById(id);

        if(oldLicense==null){
            throw new ApiExcepiton("id not found");
        }

        licenseRepository.delete(oldLicense);

    }

    public Integer calculateExpirationPeriod(Integer foodTruckId) {
        List<License> licenses = licenseRepository.findByFoodTruckId(foodTruckId);
        Integer totalDaysUntilExpiration = 0;

        for (License license : licenses) {
            Integer daysUntilExpiration = (int) ChronoUnit.DAYS.between(LocalDate.now(), license.getExpiryDate());
            totalDaysUntilExpiration += daysUntilExpiration;
        }

        return totalDaysUntilExpiration;
    }
}
