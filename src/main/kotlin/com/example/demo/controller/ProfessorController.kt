package com.example.demo.controller

import com.example.demo.dto.ProfessorRequest
import com.example.demo.dto.ProfessorResponse
import com.example.demo.service.ProfessorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/professors")
class ProfessorController(private val professorService: ProfessorService) {

    @PostMapping
    fun create(@RequestBody request: ProfessorRequest): ResponseEntity<ProfessorResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.create(request))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<ProfessorResponse>> {
        return ResponseEntity.ok(professorService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ProfessorResponse> {
        return ResponseEntity.ok(professorService.findById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ProfessorRequest): ResponseEntity<ProfessorResponse> {
        return ResponseEntity.ok(professorService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        professorService.delete(id)
        return ResponseEntity.noContent().build()
    }
}