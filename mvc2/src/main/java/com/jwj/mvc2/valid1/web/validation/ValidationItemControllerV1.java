//package com.jwj.mvc2.valid1.web.validation;
//
//import com.jwj.mvc2.valid1.domain.item.Item;
//import com.jwj.mvc2.valid1.domain.item.ItemRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/validation/v1/items")
//@RequiredArgsConstructor
//public class ValidationItemControllerV1 {
//
//    private final ItemRepository itemRepository;
//
//    @GetMapping
//    public String items(Model model) {
//        List<Item> items = itemRepository.findAll();
//        model.addAttribute("items", items);
//        return "validation/v1/items";
//    }
//
//    @GetMapping("/{itemId}")
//    public String item(@PathVariable long itemId, Model model) {
//        Item item = itemRepository.findById(itemId);
//        model.addAttribute("item", item);
//        return "validation/v1/item";
//    }
//
//    @GetMapping("/add")
//    public String addForm(Model model) {
//        model.addAttribute("item", new Item());
//        return "validation/v1/addForm";
//    }
//
//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v1/items/{itemId}";
//    }
//
//
//    @GetMapping("/{itemId}/edit")
//    public String editForm(@PathVariable Long itemId, Model model) {
//        Item item = itemRepository.findById(itemId);
//        model.addAttribute("item", item);
//        return "validation/v1/editForm";
//    }
//
//    @PostMapping("/{itemId}/edit")
//    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
//        itemRepository.update(itemId, item);
//        return "redirect:/validation/v1/items/{itemId}";
//    }
//
//}
//
