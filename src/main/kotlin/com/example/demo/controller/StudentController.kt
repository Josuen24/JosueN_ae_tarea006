package com.example.demo.controller

import com.example.demo.dto.StudentRequest
import com.example.demo.dto.StudentResponse
import com.example.demo.service.StudentService
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
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService) {

    @PostMapping
    fun create(@RequestBody request: StudentRequest): ResponseEntity<StudentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<StudentResponse>> {
        return ResponseEntity.ok(studentService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<StudentResponse> {
        return ResponseEntity.ok(studentService.findById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: StudentRequest): ResponseEntity<StudentResponse> {
        return ResponseEntity.ok(studentService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        studentService.delete(id)
        return ResponseEntity.noContent().build()
    }
}