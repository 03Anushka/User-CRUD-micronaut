package com.example.model

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class User(
        @field:NotNull(message = "Id must not be null")
      @field:Positive(message = "Id must be a positive number")
        var id: Int ,//? = null,

        @field:NotBlank(message = "Name must not be blank")
        var name: String,//? = null,

         @field:NotNull(message = "Age must not be null")
        @field:Positive(message = "Age must be a positive number")
        var age: Int //? = null
)
