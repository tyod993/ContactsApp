package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.ui.composables.ContactForm
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun AddContactWorkflow() {
    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)

    ContactForm(
        firstNameInitialValue = "",
        lastNameInitialValue = "",
        emailInitialValue = "",
        phoneNumberInitialValue = "",
        onSubmit = {
                firstName, lastName, email, phoneNumber -> sharedViewModel.submitAction(
            ContactsListAction.SubmitNewContactAction(
                 firstName, lastName, email, phoneNumber)
            )},
        onCancel ={ sharedViewModel.navController?.popBackStack()}
    )
}