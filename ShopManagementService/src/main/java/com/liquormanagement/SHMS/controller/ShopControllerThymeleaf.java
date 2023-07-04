package com.liquormanagement.SHMS.controller;

import com.liquormanagement.SHMS.entity.Shop;
import com.liquormanagement.SHMS.exception.RecordNotFoundException;
import com.liquormanagement.SHMS.service.ShopService;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.List;

@Controller
public class ShopControllerThymeleaf {

    @Autowired
    private ShopService shopService;

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);



    @RequestMapping(value= "/shops")
    public String getAllShops(Model model){
        List<Shop> allShops= null;
        try {
            allShops = shopService.getAllShops();
        }catch(RecordNotFoundException e){
            e.printStackTrace();
        }
        model.addAttribute("shops",allShops);
        return "shops";
    }

    @RequestMapping(value="/shops/new")
    public String addShopForm(Model model){
        Shop shop = new Shop();
        model.addAttribute("shop",shop);
        return "addShop";
    }
    @PostMapping(value = "/shops")
    public String registerShop(@ModelAttribute("shop") Shop shop) {
       shopService.registeringShop(shop);
       return "redirect:/shops";
    }

    @GetMapping(value = "/shops/edit/{shopId}")
    public String editShopForm(@PathVariable Long shopId,Model model) throws RecordNotFoundException {
        try {
            model.addAttribute("shop", shopService.getShopById(shopId));
        }catch (RecordNotFoundException e){
            e.printStackTrace();
        }
        return"updateShop";
    }

    @PostMapping(value = "/shops/{shopId}")
    public String updateShop(@PathVariable Long shopId,@ModelAttribute("shop")Shop shop, Model model) throws RecordNotFoundException {
        shopService.updateShop(shop);
        return "redirect:/shops";
    }

    @GetMapping(value = "/shops/{shopId}")
    public String deleteProduct(@PathVariable Long shopId)throws RecordNotFoundException{
        shopService.deleteShopByShopId(shopId);
        return "redirect:/shops";
    }

    @GetMapping(value = "/shop/products/{shopId}")
    public ModelAndView getAllProducts(@PathVariable("shopId")Long shopId) throws RecordNotFoundException {
        System.out.println("===========================");
        System.out.println("Request served at get all");
        return shopService.getProducts(shopId);
    }
/*
    @GetMapping(value = "/getShop/{shopId}")
    public String getShopById(@PathVariable("shopId") Long shopId, Model model) throws RecordNotFoundException {
        model.addAttribute("shop",shopService.getShopById(shopId));
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value="/getShop/{shopName}")
    public ResponseEntity<List<Shop>> getShopByName(@PathVariable("shopName") String shopName) throws RecordNotFoundException{
        List<Shop> shopsByName = shopService.getShopsByName(shopName);
        return (ResponseEntity<List<Shop>>) shopsByName;
    }*/

}
