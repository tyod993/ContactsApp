package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.ui.composables.DeletionConfirmationDialog
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun ContactDetails() {

    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val currentContactState = remember{ mutableStateOf(sharedViewModel.selectedContact) }
    val showConfirmationDialog by sharedViewModel.showDeletionConfirmation.observeAsState()

    Scaffold(backgroundColor = MaterialTheme.colors.background){
        if(showConfirmationDialog!!){
            DeletionConfirmationDialog(
                onConfirm = {
                    sharedViewModel.submitAction(ContactsListAction.RemoveContactAction(currentContactState.value!!))
                },
                onCancel = {
                    sharedViewModel.submitAction(ContactsListAction.CancelDeletion)
                }
            )
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text("Details:")
            }
            FieldDescription(label = "First Name", value = currentContactState.value!!.firstName)
            FieldDescription(label = "Last Name", value = currentContactState.value!!.lastName)
            FieldDescription(label = "Phone Number", value = currentContactState.value!!.phoneNumber)
            FieldDescription(label = "Email", value = currentContactState.value!!.email)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                Button(
                    onClick = { sharedViewModel.submitAction(ContactsListAction.EditContactAction(currentContactState.value!!)) }
                ){
                    Text("Edit")
                }
                Button(
                    onClick = {
                        sharedViewModel.submitAction(ContactsListAction.RequestDeletionConfirmation)
                    }
                ){
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun FieldDescription(label: String, value : String){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text("$label: ")
        Text(value)
    }
}