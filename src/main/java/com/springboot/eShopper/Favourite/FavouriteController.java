package com.springboot.eShopper.Favourite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/favourites")
public class FavouriteController {
	
	private final FavouriteService favouriteService;
	
	@Autowired
	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@GetMapping
	public List<Favourite> getAllFavourites() {
		return favouriteService.getAllFavourites();
	}
	
	@PostMapping
	public void registerNewFavourite(@RequestBody Favourite favourite) {
		favouriteService.addNewFavourite(favourite);
	}
	
	@DeleteMapping(path="{favouriteId}")
	public void deleteFavourite(@PathVariable("favouriteId") Integer favouriteId) {
		favouriteService.deleteFavourite(favouriteId);
	}
	
	@PutMapping(path="{favouriteId}")
	public void updateFavourite(@RequestBody Favourite favourite, @PathVariable Integer favouriteId) {
		favouriteService.updateFavourite(favourite, favouriteId);
	}
}
