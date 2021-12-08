package com.springboot.eShopper.Favourite;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	
	@Autowired
	public FavouriteService(FavouriteRepository favouriteRepository) {
		this.favouriteRepository = favouriteRepository;
	}
	
	public List<Favourite> getAllFavourites() {
		return favouriteRepository.findAll();
	}

	public void addNewFavourite(Favourite favourite) {
		favouriteRepository.save(favourite);		
	}

	public void deleteFavourite(Integer favouriteId) {
		// Get favourite with the given ID
		boolean exists = favouriteRepository.existsById(favouriteId);
		
		if(!exists) {
			throw new IllegalStateException("Favourite with an id of " + favouriteId + " does not exist");
		}
		
		// Else, save the favourite
		favouriteRepository.deleteById(favouriteId);
	}

	@Transactional
	public void updateFavourite(Favourite favourite, Integer favouriteId) {
		boolean exists = favouriteRepository.existsById(favouriteId);
		
		if(!exists) {
			throw new IllegalStateException("Favourite with an id of " + favouriteId + " does not exist");
		}
		
		favouriteRepository.save(favourite);
	}
}
