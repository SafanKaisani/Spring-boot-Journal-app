package com.journal.JournalApp.controller;


import com.journal.JournalApp.entity.JournalEntry;
import com.journal.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    private JournalEntryService journalEntryService;

        @GetMapping
        public ResponseEntity<?> getAll() {
            List<JournalEntry> all = journalEntryService.getAll();
             if (all != null && !all.isEmpty()) {
              return new ResponseEntity<>(journalEntryService.getAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
            try {
                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(myEntry);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping("id/{myId}")
        public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if  (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }

            return new ResponseEntity<>(journalEntry.get(), HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("id/{myId}")
        public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
        }

        @PutMapping("id/{myId}")
        public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
            JournalEntry oldEntry = journalEntryService.findById(myId).orElse(null);
            if (oldEntry != null) {
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
                journalEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }

            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
}
