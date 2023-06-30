package com.example.formvalidationcleanarchitecture.domain.use_case

import android.util.Patterns

class ValidateTerms {



    /**This execute func gonna tell the view model that email is empty */
    fun execute(acceptedTerms: Boolean) : ValidationResult {
        if(!acceptedTerms){

            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )


        }



        return ValidationResult(
            successful = true
        )

    }
}