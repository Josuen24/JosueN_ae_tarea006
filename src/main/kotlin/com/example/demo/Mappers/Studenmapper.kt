package com.example.demo.Mappers

import com.example.demo.dto.StudentRequest
import com.example.demo.dto.StudentResponse
import com.example.demo.entity.Student

fun StudentRequest.toEntity(): Student = Student(
    name = this.name,
    email = this.email
)

fun Student.toResponse(): StudentResponse = StudentResponse(
    id = this.id ?: throw IllegalStateException("El ID del estudiante no puede ser nulo al mapear"),
    name = this.name,
    email = this.email
)