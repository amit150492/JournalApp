package com.adhrit.journalApp.contoller;

import com.adhrit.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {


    @GetMapping
    public List<JournalEntry> getAll() {
        return null;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
        return true;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry DeleteJournalEntryById(@PathVariable Long myId) {
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
        return null;
    }
}
