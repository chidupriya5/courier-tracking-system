package Courier.Tracking.System.controller;

import Courier.Tracking.System.model.Parcel;
import Courier.Tracking.System.repository.ParcelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TrackingController {

    private final ParcelRepository parcelRepository;

    public TrackingController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    // Show tracking page
    @GetMapping("/track")
    public String showTrackingPage() {
        return "track";
    }

    // Search parcel by tracking ID
    @GetMapping("/track/search")
    public String trackParcel(
            @RequestParam String trackingId,
            Model model) {

        Optional<Parcel> parcel =
                parcelRepository.findByTrackingId(trackingId.trim());

        if (parcel.isPresent()) {

            model.addAttribute("parcel", parcel.get());
            model.addAttribute("found", true);

        } else {

            model.addAttribute(
                    "error",
                    "No parcel found with tracking ID: " + trackingId
            );

            model.addAttribute("found", false);
        }

        model.addAttribute("trackingId", trackingId);

        return "track";
    }
}