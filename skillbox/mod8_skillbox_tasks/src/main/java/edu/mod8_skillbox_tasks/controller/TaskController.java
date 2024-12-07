package edu.mod8_skillbox_tasks.controller;

import edu.mod8_skillbox_tasks.domain.Task;
import edu.mod8_skillbox_tasks.dto.TaskDTO;
import edu.mod8_skillbox_tasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @GetMapping
    public Flux<Task> getAllTasks() {
        return taskService.findAllWithUsers();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Task>> getById(@PathVariable String id) {
        return taskService.findById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping
    public Mono<ResponseEntity<Task>> create(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO)
            .map(ResponseEntity::ok)
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Task>> update(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @PutMapping("/{taskId}/observers/{observerId}")
    public Mono<ResponseEntity<Task>> addObserver(@PathVariable String taskId, @PathVariable String observerId) {
        return taskService.addObserver(taskId, observerId)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return taskService.deleteById(id)
            .map(success -> success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
