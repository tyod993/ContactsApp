package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.entities.Contact
import com.trevormontgomery.visacontacts.ui.composables.ContactForm
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun EditContactWorkflow() {
    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val currentContact = sharedViewModel.selectedContact!!

    ContactForm(
        firstNameInitialValue = currentContact.firstName,
        lastNameInitialValue = currentContact.lastName,
        emailInitialValue = currentContact.email,
        phoneNumberInitialValue = currentContact.phoneNumber,
        onSubmit = {
                firstName, lastName, email, phoneNumber -> sharedViewModel.submitAction(
                    ContactsListAction.SubmitEditedContact(
                        Contact(currentContact.id, firstName, lastName, email, phoneNumber)))},
        onCancel ={ sharedViewModel.navController?.popBackStack()}
    )
}