package com.journal.JournalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.journal.JournalApp.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository <JournalEntry, ObjectId> {


}
