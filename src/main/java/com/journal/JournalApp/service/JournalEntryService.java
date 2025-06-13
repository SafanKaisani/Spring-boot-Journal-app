package com.journal.JournalApp.service;

import com.journal.JournalApp.entity.JournalEntry;
import com.journal.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() { return journalEntryRepository.findAll(); }

    public Optional<JournalEntry> findById(ObjectId id) { return journalEntryRepository.findById(id); }

    public void deleteById(ObjectId id) { journalEntryRepository.deleteById(id); }
}
