package com.example.demo.service

import com.example.demo.Mappers.toEntity
import com.example.demo.Mappers.toResponse
import com.example.demo.dto.SubjectRequest
import com.example.demo.dto.SubjectResponse
import com.example.demo.entity.Subject
import com.example.demo.exeptions.ProfessorNotFoundException
import com.example.demo.exeptions.SubjectNotFoundException
import com.example.demo.repository.ProfessorRepository
import com.example.demo.repository.SubjectRepository
import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val professorRepository: ProfessorRepository
) {

    fun create(request: SubjectRequest): SubjectResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        require(request.code.isNotBlank()) { "Code cannot be blank" }
        val professor = professorRepository.findById(request.professorId)
            .orElseThrow { ProfessorNotFoundException("Professor with id ${request.professorId} not found") }
        return subjectRepository.save(request.toEntity(professor)).toResponse()
    }

    fun findAll(): List<SubjectResponse> {
        return subjectRepository.findAll().map { it.toResponse() }
    }

    fun findById(id: Long): SubjectResponse {
        return subjectRepository.findById(id)
            .orElseThrow { SubjectNotFoundException("Subject with id $id not found") }
            .toResponse()
    }

    fun update(id: Long, request: SubjectRequest): SubjectResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        require(request.code.isNotBlank()) { "Code cannot be blank" }
        val existing = subjectRepository.findById(id)
            .orElseThrow { SubjectNotFoundException("Subject with id $id not found") }
        val professor = professorRepository.findById(request.professorId)
            .orElseThrow { ProfessorNotFoundException("Professor with id ${request.professorId} not found") }
        val updated = Subject(
            id = existing.id,
            name = request.name,
            code = request.code,
            professor = professor
        )
        return subjectRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!subjectRepository.existsById(id)) {
            throw SubjectNotFoundException("Subject with id $id not found")
        }
        subjectRepository.deleteById(id)
    }
}