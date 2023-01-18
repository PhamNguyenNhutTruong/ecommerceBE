package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, String>{

}
