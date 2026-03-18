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
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + userName);
            }
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception exception) {
            // Log and rollback (RuntimeException triggers rollback automatically)
            throw new RuntimeException("Failed to save journal entry", exception);
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

    @Transactional
    public boolean deleteById(Long id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(String.valueOf(id));
            }
        } catch (Exception exception) {
            // Log and rollback (RuntimeException triggers rollback automatically)
            throw new RuntimeException("Failed to delete journal entry", exception);
        }
        return removed;

    }

    public List<JournalEntry> findByUserName(String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            return user.getJournalEntries();
        }
        return List.of(); // Return empty list if user not found
    }
}
