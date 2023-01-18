package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String>{

}
