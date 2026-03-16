package com.adhrit.journalApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries") // Maps this entity to the MySQL table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false) // Replaces @NonNull for database-level validation
    private String title;

    @Column(columnDefinition = "TEXT") // Allows for longer content strings
    private String content;

    private LocalDateTime date;
}