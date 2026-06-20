package com.example.demo.Mappers

import com.example.demo.dto.ProfessorRequest
import com.example.demo.dto.ProfessorResponse
import com.example.demo.entity.Professor

fun ProfessorRequest.toEntity(): Professor = Professor(
    name = this.name,
    email = this.email
)

fun Professor.toResponse(): ProfessorResponse = ProfessorResponse(
    id = this.id!!,
    name = this.name,
    email = this.email
)