package com.adhrit.journalApp.Repository;

import com.adhrit.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
