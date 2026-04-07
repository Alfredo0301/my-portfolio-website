package com.portfolio.PortfolioWebsite.controller;

import com.portfolio.PortfolioWebsite.Model.ContactMessage;
import com.portfolio.PortfolioWebsite.Repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @GetMapping
    public String contactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping
    public String submitContact(@ModelAttribute ContactMessage contactMessage, RedirectAttributes redirectAttributes) {
        try {
            contactMessageRepository.save(contactMessage);
            redirectAttributes.addFlashAttribute("successMessage", "Mensagem enviada com sucesso! Obrigado por entrar em contato.");
            return "redirect:/contact";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao enviar mensagem. Tente novamente.");
            return "redirect:/contact";
        }
    }

    @PostMapping("/api/send")
    @ResponseBody
    public Map<String, Object> submitContactApi(@RequestBody ContactMessage contactMessage) {
        Map<String, Object> response = new HashMap<>();
        try {
            contactMessageRepository.save(contactMessage);
            response.put("success", true);
            response.put("message", "Mensagem enviada com sucesso!");
            return response;
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro ao enviar mensagem");
            return response;
        }
    }
}



