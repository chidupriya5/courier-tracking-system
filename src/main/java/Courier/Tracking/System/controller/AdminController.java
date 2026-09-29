package Courier.Tracking.System.controller;

import Courier.Tracking.System.enums.ParcelStatus;
import Courier.Tracking.System.model.Parcel;
import Courier.Tracking.System.repository.ParcelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    private final ParcelRepository parcelRepository;

    public AdminController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @GetMapping("/admin")
    public String showAdminPanel(Model model) {

        model.addAttribute("parcels", parcelRepository.findAll());
        model.addAttribute("statuses", ParcelStatus.values());

        return "admin";
    }

    @PostMapping("/admin/update-status")
    public String updateStatus(
            @RequestParam Long parcelId,
            @RequestParam String status) {

        Optional<Parcel> parcelOpt = parcelRepository.findById(parcelId);

        if (parcelOpt.isPresent()) {

            Parcel parcel = parcelOpt.get();

            try {
                ParcelStatus parcelStatus =
                        ParcelStatus.valueOf(status.toUpperCase());

                parcel.setStatus(parcelStatus);

                parcelRepository.save(parcel);

            } catch (IllegalArgumentException e) {
                // Invalid status received from the form.
                // Do not update the parcel.
            }
        }

        return "redirect:/admin";
    }
}