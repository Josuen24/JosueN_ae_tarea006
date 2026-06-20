package com.example.demo.controller

import com.example.demo.dto.EnrollmentRequest
import com.example.demo.dto.EnrollmentResponse
import com.example.demo.dto.EnrollmentStatusRequest
import com.example.demo.service.EnrollmentService
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
@RequestMapping("/api/enrollments")
class EnrollmentController(private val enrollmentService: EnrollmentService) {

    @PostMapping
    fun create(@RequestBody request: EnrollmentRequest): ResponseEntity<EnrollmentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(request))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<EnrollmentResponse>> {
        return ResponseEntity.ok(enrollmentService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<EnrollmentResponse> {
        return ResponseEntity.ok(enrollmentService.findById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: EnrollmentStatusRequest): ResponseEntity<EnrollmentResponse> {
        return ResponseEntity.ok(enrollmentService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        enrollmentService.delete(id)
        return ResponseEntity.noContent().build()
    }
}