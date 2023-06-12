package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.BasketService;

@RestController
@RequestMapping("/ssafit")
public class BasketRestController {

	@Autowired
	BasketService basketService;
	
	@GetMapping("/basket/{userId}")
	public List<Video> basketList(@PathVariable String userId) {
		return basketService.getAllBasketList(userId);
	}
	
	@GetMapping("/basket/{userId}/{videoId}")
	public boolean checkBasket(@PathVariable String userId, @PathVariable String videoId) {
		return basketService.checkBasket(userId, videoId);
	}
	
	@PostMapping("/basket/{userId}/{videoId}")
	public ResponseEntity<Void> addBasket(@PathVariable String userId, @PathVariable String videoId){

		basketService.addBasket(userId, videoId);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/basket/{userId}/{videoId}")
	public ResponseEntity<Void> delete(@PathVariable String userId, @PathVariable String videoId){
		basketService.removeBasket(userId, videoId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
