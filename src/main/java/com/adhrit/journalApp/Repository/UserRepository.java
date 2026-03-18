package com.adhrit.journalApp.Repository;

import com.adhrit.journalApp.entity.JournalEntry;
import com.adhrit.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
