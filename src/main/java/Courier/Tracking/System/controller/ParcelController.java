package Courier.Tracking.System.controller;

import Courier.Tracking.System.enums.ParcelStatus;
import Courier.Tracking.System.model.Parcel;
import Courier.Tracking.System.repository.ParcelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ParcelController {

    private final ParcelRepository parcelRepository;

    public ParcelController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @GetMapping({"/booking", "/book"})
    public String showBookingForm(Model model) {

        model.addAttribute("parcel", new Parcel());

        return "booking";
    }

    @PostMapping({"/booking", "/book"})
    public String processBooking(
            @ModelAttribute Parcel parcel,
            Model model) {

        // Generate a unique tracking ID
        // Example: CRX-A1B2C3D4
        String trackingId =
                "CRX-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        parcel.setTrackingId(trackingId);

        // New parcel starts with BOOKED status
        parcel.setStatus(ParcelStatus.BOOKED);

        parcelRepository.save(parcel);

        model.addAttribute("trackingId", trackingId);

        return "booking-success";
    }
}