package edu.mod3_skillbox.repository;

import lombok.RequiredArgsConstructor;
import edu.mod3_skillbox.model.Contact;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM contacts", BeanPropertyRowMapper.newInstance(Contact.class));
    }

    public void add(String firstName, String secondName, String email, String phoneNumber) {
        jdbcTemplate.update("INSERT INTO contacts(first_name, second_name, email, phone_number) VALUES(?, ?, ?, ?);", firstName, secondName, email, phoneNumber);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?;", id);
    }

    public void update(long id, String firstName, String secondName, String email, String phoneNumber) {
        jdbcTemplate.update("UPDATE contacts SET first_name= ?, second_name=?, email=?, phone_number=? WHERE id=?;", firstName, secondName, email, phoneNumber, id);
    }

    public Contact getById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM contacts WHERE id=?", new Object[]{id}, BeanPropertyRowMapper.newInstance(Contact.class));
    }
}