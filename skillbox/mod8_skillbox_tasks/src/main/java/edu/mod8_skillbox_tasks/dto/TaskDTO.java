package edu.mod8_skillbox_tasks.dto;

import edu.mod8_skillbox_tasks.domain.TaskStatus;

import java.time.Instant;
import java.util.Set;

public record TaskDTO(String id,
                      String name,
                      String description,
                      Instant createdAt,
                      Instant updatedAt,
                      TaskStatus status,
                      String authorId,
                      String assigneeId,
                      Set<String> observerIds) {
}
