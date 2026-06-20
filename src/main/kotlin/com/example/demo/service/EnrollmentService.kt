package com.example.demo.service

import com.example.demo.Mappers.toResponse
import com.example.demo.dto.EnrollmentRequest
import com.example.demo.dto.EnrollmentResponse
import com.example.demo.dto.EnrollmentStatusRequest
import com.example.demo.entity.Enrollment
import com.example.demo.exeptions.EnrollmentNotFoundException
import com.example.demo.exeptions.StudentNotFoundException
import com.example.demo.exeptions.SubjectNotFoundException
import com.example.demo.repository.EnrollmentRepository
import com.example.demo.repository.StudentRepository
import com.example.demo.repository.SubjectRepository
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
    private val enrollmentRepository: EnrollmentRepository,
    private val studentRepository: StudentRepository,
    private val subjectRepository: SubjectRepository
) {

    fun create(request: EnrollmentRequest): EnrollmentResponse {
        val student = studentRepository.findById(request.studentId)
            .orElseThrow { StudentNotFoundException("Student with id ${request.studentId} not found") }
        val subject = subjectRepository.findById(request.subjectId)
            .orElseThrow { SubjectNotFoundException("Subject with id ${request.subjectId} not found") }
        val enrollment = Enrollment(
            student = student,
            subject = subject
        )
        return enrollmentRepository.save(enrollment).toResponse()
    }

    fun findAll(): List<EnrollmentResponse> {
        return enrollmentRepository.findAll().map { it.toResponse() }
    }

    fun findById(id: Long): EnrollmentResponse {
        return enrollmentRepository.findById(id)
            .orElseThrow { EnrollmentNotFoundException("Enrollment with id $id not found") }
            .toResponse()
    }

    fun update(id: Long, request: EnrollmentStatusRequest): EnrollmentResponse {
        val existing = enrollmentRepository.findById(id)
            .orElseThrow { EnrollmentNotFoundException("Enrollment with id $id not found") }
        val updated = Enrollment(
            id = existing.id,
            student = existing.student,
            subject = existing.subject,
            status = request.status,
            createdAt = existing.createdAt
        )
        return enrollmentRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!enrollmentRepository.existsById(id)) {
            throw EnrollmentNotFoundException("Enrollment with id $id not found")
        }
        enrollmentRepository.deleteById(id)
    }
}