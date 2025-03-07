package com.jwj.mvc2.upload.controller;

import com.jwj.mvc2.upload.domain.dto.DownloadResponse;
import com.jwj.mvc2.upload.domain.dto.ItemForm;
import com.jwj.mvc2.upload.domain.entity.Item;
import com.jwj.mvc2.upload.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@GetMapping("/items/new")
	public String newItem(@ModelAttribute ItemForm form) {
		return "item-form";
	}

	@PostMapping("/items/new")
	public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {

		redirectAttributes.addAttribute("itemId", itemService.save(form));
		return "redirect:/items/{itemId}";
	}

	@GetMapping("/items/{id}")
	public String items(@PathVariable Long id, Model model){
		Item item = itemService.findItem(id);
		model.addAttribute("item",item);
		return "item-view";
	}

	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		return itemService.getResource(filename);
	}

	@GetMapping("/attach/{itemId}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
		DownloadResponse download = itemService.download(itemId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,download.getContentDisposition())
				.body(download.getResource());
	}

}