package Courier.Tracking.System;

import Courier.Tracking.System.enums.ParcelStatus;
import Courier.Tracking.System.model.Parcel;
import Courier.Tracking.System.repository.ParcelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ParcelRepository parcelRepository;

    public DashboardController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        List<Parcel> parcels = parcelRepository.findAll();

        // Count all parcels except delivered parcels
        long activeCount = parcels.stream()
                .filter(p -> !ParcelStatus.DELIVERED.equals(p.getStatus()))
                .count();

        // Count delivered parcels
        long deliveredCount = parcels.stream()
                .filter(p -> ParcelStatus.DELIVERED.equals(p.getStatus()))
                .count();

        model.addAttribute("parcels", parcels);
        model.addAttribute("activeShipmentsCount", activeCount);
        model.addAttribute("deliveredCount", deliveredCount);

        return "dashboard";
    }
}