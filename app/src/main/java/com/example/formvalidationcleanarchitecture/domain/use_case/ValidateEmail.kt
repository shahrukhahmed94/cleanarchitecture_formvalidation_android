package com.example.formvalidationcleanarchitecture.domain.use_case

import android.util.Patterns

class ValidateEmail {



    /**This execute func gonna tell the view model that email is empty */
    fun execute(email: String) : ValidationResult {
        if(email.isBlank()){

            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )


        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid Email Address"
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}