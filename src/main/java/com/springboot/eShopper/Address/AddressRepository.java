package com.springboot.eShopper.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Shopper.Shopper;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> getAddressesByShopper(Shopper shopper);
}
