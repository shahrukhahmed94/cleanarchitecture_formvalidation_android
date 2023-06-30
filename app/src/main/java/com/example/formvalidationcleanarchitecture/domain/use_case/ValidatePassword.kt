package com.example.formvalidationcleanarchitecture.domain.use_case

import android.util.Patterns

class ValidatePassword {



    /**This execute func gonna tell the view model that email is empty */
    fun execute(password: String) : ValidationResult {
        if(password.length<8){

            return ValidationResult(
                successful = false,
                errorMessage = "The password must be at least 8 characters"
            )


        }

        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if(!containsLettersAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contains at least one letter and number"
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}