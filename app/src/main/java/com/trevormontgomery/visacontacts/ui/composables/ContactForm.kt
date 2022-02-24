package com.trevormontgomery.visacontacts.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ContactForm(
    firstNameInitialValue: String,
    lastNameInitialValue : String,
    emailInitialValue : String,
    phoneNumberInitialValue : String,
    onSubmit: (firstName : String, lastName : String, email : String, phoneNumber : String) -> Unit,
    onCancel: () -> Unit
) {

    Scaffold(backgroundColor = MaterialTheme.colors.background) {
        Column {

            val firstNameFieldState = remember { mutableStateOf(TextFieldValue(firstNameInitialValue)) }
            val lastNameFieldState = remember { mutableStateOf(TextFieldValue(lastNameInitialValue)) }
            val phoneNumberFieldState = remember{ mutableStateOf(TextFieldValue(phoneNumberInitialValue)) }
            val emailFieldState = remember{ mutableStateOf(TextFieldValue(emailInitialValue)) }

            val allInputsValidated = remember{ mutableStateOf(false)}

            Box(modifier = Modifier
                .padding(all = 10.dp)
                .align(Alignment.CenterHorizontally)){
                Text("Add Contact")
            }
            ErrorLabeledTextField(
                label = "First Name",
                validationState = allInputsValidated,
                textState = firstNameFieldState,
                validationRequirements = { it.trim().length > 2} ,
                errorMessage = "First name must be at least 2 characters long." )
            ErrorLabeledTextField(
                label = "Last Name",
                validationState = allInputsValidated,
                textState = lastNameFieldState,
                validationRequirements = { it.trim().length > 2} ,
                errorMessage = "Last name must be at least 2 characters long." )
            ErrorLabeledTextField(
                label = "Phone Number",
                validationState = allInputsValidated,
                textState = phoneNumberFieldState,
                validationRequirements = { it.contains(Regex("^[0-9]")) && it.length == 10} ,
                errorMessage = "Phone number must be 10 digits only example : 2535555555" )
            ErrorLabeledTextField(
                label = "Email",
                validationState = allInputsValidated,
                textState = emailFieldState,
                validationRequirements = { it.contains(Regex("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}"))} ,
                errorMessage = "Email must contain @ character. Example : email@email.com" )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                Button(
                    onClick = { onCancel() }
                ){
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        if(allInputsValidated.value){
                            onSubmit(
                                firstNameFieldState.value.text,
                                lastNameFieldState.value.text,
                                emailFieldState.value.text,
                                phoneNumberFieldState.value.text
                            )
                        }
                    }
                ){
                    Text("Submit")
                }
            }
        }
    }
}

@Composable
fun ErrorLabeledTextField(
    label : String,
    validationState : MutableState<Boolean>,
    textState : MutableState<TextFieldValue>,
    validationRequirements : (String) -> Boolean,
    errorMessage : String
){

    val invalidInput = !validationRequirements(textState.value.text)
    val textColor = if (invalidInput) {
        MaterialTheme.colors.error
    } else {
        MaterialTheme.colors.onSurface
    }

    OutlinedTextField(
        value = textState.value,
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        onValueChange = {
            textState.value = it
            validationState.value = !invalidInput
                        },
        label = {
            if(invalidInput){
                Text("$label*")
            } else {
                Text(label)
            }
        },
        isError = invalidInput
    )

    Text(
        modifier = Modifier
            .padding(start = 16.dp),
        textAlign = TextAlign.Center,
        text = if (invalidInput) errorMessage else "",
        style = MaterialTheme.typography.caption.copy(color = textColor),
    )

}