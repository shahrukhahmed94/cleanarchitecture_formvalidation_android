package com.example.formvalidationcleanarchitecture.domain.use_case

import android.util.Patterns

class ValidateRepeatedPassword {



    /**This execute func gonna tell the view model that email is empty */
    fun execute(password: String, repeatedPassword: String) : ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password don't match"
            )
        }


                return ValidationResult(
            successful = true
        )

    }
}