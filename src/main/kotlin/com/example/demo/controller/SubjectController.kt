package com.example.demo.controller

import com.example.demo.dto.SubjectRequest
import com.example.demo.dto.SubjectResponse
import com.example.demo.service.SubjectService
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
@RequestMapping("/api/subjects")
class SubjectController(private val subjectService: SubjectService) {

    @PostMapping
    fun create(@RequestBody request: SubjectRequest): ResponseEntity<SubjectResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.create(request))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<SubjectResponse>> {
        return ResponseEntity.ok(subjectService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<SubjectResponse> {
        return ResponseEntity.ok(subjectService.findById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: SubjectRequest): ResponseEntity<SubjectResponse> {
        return ResponseEntity.ok(subjectService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        subjectService.delete(id)
        return ResponseEntity.noContent().build()
    }
}