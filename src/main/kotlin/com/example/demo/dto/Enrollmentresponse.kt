package com.example.demo.dto

import java.time.LocalDateTime

class EnrollmentResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val status: String,
    val student: StudentResponse,
    val subject: SubjectResponse
)