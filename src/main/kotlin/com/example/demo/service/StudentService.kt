package com.example.demo.service

import com.example.demo.Mappers.toEntity
import com.example.demo.Mappers.toResponse
import com.example.demo.repository.StudentRepository
import com.example.demo.exceptions.BlankMesaggeException
import com.example.demo.exceptions.StudentNotFoundException
import com.example.demo.dto.StudentResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    private val logger = LoggerFactory.getLogger(StudentService::class.java)

    fun createStudent(request: StudentRequest): StudentResponse {
        if (request.name.isNullOrBlank()) {
            throw BlankMesaggeException("El nombre del estudiante no puede estar en blanco")
        }

        val student = request.toEntity()
        val savedStudent = studentRepository.save(student)


        return savedStudent.toResponse()
    }

    fun getStudentById(id: Long): StudentResponse {
        logger.info("Getting student by id: $id")

        val student = studentRepository.findById(id)
            .orElseThrow { StudentNotFoundException("No se encontró el estudiante con ID: $id") }


        return student.toResponse()
    }

    fun getAllStudents(): List<StudentResponse> {

        return studentRepository.findAll().map { it.toResponse() }
    }
}