package com.example.demo.service

import com.example.demo.Mappers.toEntity
import com.example.demo.Mappers.toResponse
import com.example.demo.dto.ProfessorRequest
import com.example.demo.dto.ProfessorResponse
import com.example.demo.exeptions.ProfessorNotFoundException
import com.example.demo.repository.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class ProfessorService(private val professorRepository: ProfessorRepository) {

    fun create(request: ProfessorRequest): ProfessorResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        return professorRepository.save(request.toEntity()).toResponse()
    }

    fun findAll(): List<ProfessorResponse> {
        return professorRepository.findAll().map { it.toResponse() }
    }

    fun findById(id: Long): ProfessorResponse {
        return professorRepository.findById(id)
            .orElseThrow { ProfessorNotFoundException("Professor with id $id not found") }
            .toResponse()
    }

    fun update(id: Long, request: ProfessorRequest): ProfessorResponse {
        require(request.name.isNotBlank()) { "Name cannot be blank" }
        val existing = professorRepository.findById(id)
            .orElseThrow { ProfessorNotFoundException("Professor with id $id not found") }
        val updated = com.example.demo.entity.Professor(
            id = existing.id,
            name = request.name,
            email = request.email
        )
        return professorRepository.save(updated).toResponse()
    }

    fun delete(id: Long) {
        if (!professorRepository.existsById(id)) {
            throw ProfessorNotFoundException("Professor with id $id not found")
        }
        professorRepository.deleteById(id)
    }
}