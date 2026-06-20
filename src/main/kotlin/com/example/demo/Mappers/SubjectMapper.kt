package com.example.demo.Mappers

import com.example.demo.dto.SubjectRequest
import com.example.demo.dto.SubjectResponse
import com.example.demo.entity.Professor
import com.example.demo.entity.Subject

fun SubjectRequest.toEntity(professor: Professor): Subject = Subject(
    name = this.name,
    code = this.code,
    professor = professor
)

fun Subject.toResponse(): SubjectResponse = SubjectResponse(
    id = this.id!!,
    name = this.name,
    code = this.code,
    professor = this.professor.toResponse()
)