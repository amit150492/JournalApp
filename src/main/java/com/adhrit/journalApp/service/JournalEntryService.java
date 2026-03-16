package com.adhrit.journalApp.service;

import com.adhrit.journalApp.Repository.JournalEntryRepository;
import com.adhrit.journalApp.entity.JournalEntry;
import com.adhrit.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + userName);
            }
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveNewUser(user);
        } catch (Exception exception) {
            System.err.println("[JournalEntryService] Failed to save journal entry: " + exception.getMessage());
            throw new RuntimeException("Failed to save journal entry: " + exception.getMessage(), exception);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String id, String userName) {
        User user = userService.findByUsername(userName);
        if (user != null) {
            user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            userService.saveNewUser(user);
        }
        journalEntryRepository.deleteById(id);
    }
}
