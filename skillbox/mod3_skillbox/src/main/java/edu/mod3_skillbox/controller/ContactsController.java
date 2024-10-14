package edu.mod3_skillbox.controller;

import edu.mod3_skillbox.model.Contact;
import edu.mod3_skillbox.dto.ContactDTO;
import edu.mod3_skillbox.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/contacts/")
@RequiredArgsConstructor
public class ContactsController {
    private final ContactService contactService;

    @GetMapping("getAll")
    public String getAll(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "main-contacts";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("contactDTO", new ContactDTO(null, null, null, null));
        return "add-update";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("contactDTO") ContactDTO contactDTO) {
        contactService.add(contactDTO);
        return "redirect:/api/contacts/getAll";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        var contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "add-update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("contact") Contact contact) {
        contactService.update(contact);
        return "redirect:/api/contacts/getAll";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute("id") long id) {
        contactService.delete(id);
        return "redirect:/api/contacts/getAll";
    }
}