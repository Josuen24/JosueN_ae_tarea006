package com.example.demo.service

import com.example.demo.Mappers.toEntity
import com.example.demo.Mappers.toResponse
import com.example.demo.dto.StudentRequest
import com.example.demo.dto.StudentResponse
import com.example.demo.entity.Student
import com.example.demo.exeptions.StudentNotFoundException
import com.example.demo.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun create(request: StudentRequest): StudentResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        return studentRepository.save(request.toEntity()).toResponse()
    }

    fun findAll(): List<StudentResponse> {
        return studentRepository.findAll().map { it.toResponse() }
    }

    fun findById(id: Long): StudentResponse {
        return studentRepository.findById(id)
            .orElseThrow { StudentNotFoundException("Student with id $id not found") }
            .toResponse()
    }

    fun update(id: Long, request: StudentRequest): StudentResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        val existing = studentRepository.findById(id)
            .orElseThrow { StudentNotFoundException("Student with id $id not found") }
        val updated = Student(
            id = existing.id,
            name = request.name,
            email = request.email
        )
        return studentRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!studentRepository.existsById(id)) {
            throw StudentNotFoundException("Student with id $id not found")
        }
        studentRepository.deleteById(id)
    }
}