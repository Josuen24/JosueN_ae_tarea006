package com.example.demo.controller

import com.example.demo.dto.StudentResponse
import com.example.demo.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.* // Import necesario para @PathVariable y @GetMapping

@RestController
@RequestMapping("/students")
class StudentController(private val studentService: StudentService) {

    @PostMapping
    fun createStudent(@RequestBody request: StudentRequest): ResponseEntity<StudentResponse> {
        val response = studentService.createStudent(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllStudents(): ResponseEntity<List<StudentResponse>> {
        val response = studentService.getAllStudents()
        return ResponseEntity(response, HttpStatus.OK)
    }

    
    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): ResponseEntity<StudentResponse> {
        val response = studentService.getStudentById(id)
        return ResponseEntity.ok(response)
    }
}