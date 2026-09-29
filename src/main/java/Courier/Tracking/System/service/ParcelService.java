package Courier.Tracking.System.service;

import Courier.Tracking.System.enums.ParcelStatus;
import Courier.Tracking.System.model.Parcel;
import Courier.Tracking.System.repository.ParcelRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    /**
     * Generate a unique tracking ID.
     */
    public String generateTrackingId() {

        String trackingId;

        do {
            trackingId = "CTS-" +
                    UUID.randomUUID()
                            .toString()
                            .substring(0, 8)
                            .toUpperCase();

        } while (parcelRepository.findByTrackingId(trackingId).isPresent());

        return trackingId;
    }

    /**
     * Book a new parcel.
     */
    public Parcel bookParcel(
            String senderName,
            String receiverName,
            String receiverAddress,
            String receiverPhone,
            double weight) {

        String trackingId = generateTrackingId();

        Parcel parcel = new Parcel(
                trackingId,
                senderName,
                receiverName,
                receiverAddress,
                receiverPhone,
                weight,
                ParcelStatus.BOOKED
        );

        return parcelRepository.save(parcel);
    }

    /**
     * Find parcel using tracking ID.
     */
    public Optional<Parcel> trackParcel(String trackingId) {

        return parcelRepository.findByTrackingId(trackingId);
    }

    /**
     * Get all parcels.
     */
    public List<Parcel> getAllParcels() {

        return parcelRepository.findAll();
    }

    /**
     * Update parcel status.
     */
    public Optional<Parcel> updateStatus(
            String trackingId,
            ParcelStatus newStatus) {

        Optional<Parcel> optionalParcel =
                parcelRepository.findByTrackingId(trackingId);

        if (optionalParcel.isEmpty()) {
            return Optional.empty();
        }

        Parcel parcel = optionalParcel.get();

        parcel.setStatus(newStatus);

        return Optional.of(
                parcelRepository.save(parcel)
        );
    }
}