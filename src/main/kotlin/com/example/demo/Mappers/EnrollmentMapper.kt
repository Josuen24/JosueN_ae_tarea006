package com.example.demo.Mappers

import com.example.demo.dto.EnrollmentResponse
import com.example.demo.entity.Enrollment

fun Enrollment.toResponse(): EnrollmentResponse = EnrollmentResponse(
    id = this.id!!,
    createdAt = this.createdAt,
    status = this.status,
    student = this.student.toResponse(),
    subject = this.subject.toResponse()
)