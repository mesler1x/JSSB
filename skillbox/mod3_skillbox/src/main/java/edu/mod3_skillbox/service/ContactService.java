package edu.mod3_skillbox.service;

import edu.mod3_skillbox.model.Contact;
import edu.mod3_skillbox.dto.ContactDTO;
import edu.mod3_skillbox.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public List<Contact> getAll(){
        return contactRepository.findAll();
    }

    public void add(ContactDTO contactDTO) {
        contactRepository.add(
                contactDTO.firstName(),
                contactDTO.secondName(),
                contactDTO.email(),
                contactDTO.phoneNumber());
    }

    public void update(Contact contactDTO) {
        contactRepository.update(
                contactDTO.getId(),
                contactDTO.getFirstName(),
                contactDTO.getSecondName(),
                contactDTO.getEmail(),
                contactDTO.getPhoneNumber());
    }

    public void delete(long id) {
        contactRepository.delete(id);
    }

    public Contact getById(long id) {
        return contactRepository.getById(id);
    }
}