package com.springboot.eShopper.Purchase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Address.Address;
import com.springboot.eShopper.Address.AddressRepository;
import com.springboot.eShopper.Cart.Cart;
import com.springboot.eShopper.Cart.CartRepository;
import com.springboot.eShopper.CartItem.CartItemRepository;
import com.springboot.eShopper.PurchaseItem.PurchaseItem;
import com.springboot.eShopper.PurchaseItem.PurchaseItemRepository;
import com.springboot.eShopper.Shopper.Shopper;
import com.springboot.eShopper.Shopper.ShopperRepository;
import com.springboot.eShopper.CartItem.CartItem;

@Service
public class PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	private final PurchaseItemRepository purchaseItemRepository;
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final AddressRepository addressRepository;
	private final ShopperRepository shopperRepository;
	
	@Autowired
	public PurchaseService(PurchaseRepository purchaseRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, PurchaseItemRepository purchaseItemRepository, AddressRepository addressRepository, ShopperRepository shopperRepository) {
		this.purchaseRepository = purchaseRepository;
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.purchaseItemRepository = purchaseItemRepository;
		this.addressRepository = addressRepository;
		this.shopperRepository = shopperRepository;
	}
	
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}
	
	public Purchase getPurchaseById(Integer purchaseId) {
		// Check if purchase exists
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
				
		return purchaseRepository.findById(purchaseId).get();
	}
	
	public List<Purchase> getPurchasesByShopperId(Integer shopperId) {
		// Check if shopper exists
		boolean exists = shopperRepository.existsById(shopperId);
		
		if(!exists) {
			throw new IllegalStateException("Shopper with an id of " + shopperId + " does not exist");
		}
		
		Shopper shopper = shopperRepository.getById(shopperId);
		
		return purchaseRepository.getPurchasesByShopper(shopper);
	}

	public void addNewPurchase(Integer cartId) {
		// Check if purchase exists
		boolean exists = cartRepository.existsById(cartId);
		
		if(!exists) {
			throw new IllegalStateException("Cart with an id of " + cartId + " does not exist");
		}
		
		// Get cart and shopper
		Cart cart = cartRepository.getById(cartId);
		Shopper shopper = cart.getShopper();
		
		// Get cart items
		List<CartItem> cartItemsToConvert = cartItemRepository.getCartItemsByCart(cart).get();
		
		// Create new purchase
		Purchase newPurchase = new Purchase();
		
		// Set shopper associated to this purchase
		newPurchase.setShopper(shopper);
		
		// Save new purchase
		purchaseRepository.save(newPurchase);
		
		// Create purchase items 
		for(CartItem c : cartItemsToConvert) {
			PurchaseItem newPurchaseItem = new PurchaseItem();
			
			newPurchaseItem.setProductName(c.getProductName());
			newPurchaseItem.setProductPrice(c.getProductPrice());
			newPurchaseItem.setStockCount(c.getStockCount());
			newPurchaseItem.setItemCount(c.getItemCount());
			
			// Check that stock count is not greater than item count
			if(newPurchaseItem.getStockCount() < newPurchaseItem.getItemCount()) {
				throw new IllegalStateException("Quantity exceeds the quantity of " + newPurchaseItem.getProductName() + " in stock");
			}
			
			// Set purchase associated to this purchaseItem
			newPurchaseItem.setPurchase(newPurchase);
			
			purchaseItemRepository.save(newPurchaseItem);
		}
	}
	
	public List<Date> addDeliveryAddress(Integer purchaseId, Integer addressId) throws ParseException {
		// Check if purchase exists
		boolean purchaseExists = purchaseRepository.existsById(purchaseId);
		boolean addressExists = addressRepository.existsById(addressId);
		
		if(!purchaseExists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		if(!addressExists) {
			throw new IllegalStateException("Address with an id of " + addressId + " does not exist");
		}
		
		// Get purchase and address
		Purchase purchase = purchaseRepository.getById(purchaseId);
		Address deliveryAddress = addressRepository.getById(addressId);
		
		// Update and save purchase
		purchase.setShopperAddress(deliveryAddress);
		purchaseRepository.save(purchase);
		
		// Return delivery windows for this address
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<Date> deliveryWindows = new ArrayList<Date>(Arrays.asList( 
				dateFormatter.parse("2021/12/20 10:00:00"),
				dateFormatter.parse("2021/12/20 12:30:00"),
				dateFormatter.parse("2021/12/20 15:00:00"),
				dateFormatter.parse("2021/12/21 10:00:00"),
				dateFormatter.parse("2021/12/21 12:30:00"),
				dateFormatter.parse("2021/12/21 15:00:00"),
				dateFormatter.parse("2021/12/22 10:00:00"),
				dateFormatter.parse("2021/12/22 12:30:00"),
				dateFormatter.parse("2021/12/22 15:00:00")
			)
		);
		
		return deliveryWindows;
	}
	
	public void addDeliveryWindow(Integer purchaseId, String deliveryWindowString) throws ParseException {
		// Check if purchase exists
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		// Get purchase
		Purchase purchase = purchaseRepository.getById(purchaseId);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date deliveryWindow = dateFormatter.parse(deliveryWindowString);
		
		// Add delivery window
		purchase.setDeliveryWindow(deliveryWindow);
		
		// Add delivery fee
		purchase.setDeliveryFee(1000.00);
		
		// Update purchase in DB
		purchaseRepository.save(purchase);
	}
	
	public void addPaymentDetails() {
		
	}

	public void deletePurchase(Integer purchaseId) {
		// Check if purchase exists
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		// Else, save the purchase
		purchaseRepository.deleteById(purchaseId);
	}

	@Transactional
	public void updatePurchase(Purchase purchase, Integer purchaseId) {
		// Check if purchase exists
		boolean exists = purchaseRepository.existsById(purchaseId);
		
		if(!exists) {
			throw new IllegalStateException("Purchase with an id of " + purchaseId + " does not exist");
		}
		
		purchaseRepository.save(purchase);
	}
}
