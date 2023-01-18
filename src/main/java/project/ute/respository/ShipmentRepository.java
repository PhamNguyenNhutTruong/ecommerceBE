package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.ute.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {

}