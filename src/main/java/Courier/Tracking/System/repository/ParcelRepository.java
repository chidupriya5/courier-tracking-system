package Courier.Tracking.System.repository;

import Courier.Tracking.System.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    Optional<Parcel> findByTrackingId(String trackingId);
}