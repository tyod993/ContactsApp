package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.ui.composables.DeletionConfirmationDialog
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun ContactDetails(){

    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val currentContactState = remember{ mutableStateOf(sharedViewModel.selectedContact) }
    val showConfirmationDialog by sharedViewModel.showDeletionConfirmation.observeAsState()

    Scaffold(backgroundColor = MaterialTheme.colors.background){
        if(showConfirmationDialog!!){
            DeletionConfirmationDialog(
                onConfirm = {
                    sharedViewModel.submitAction(
                        ContactsListAction.RemoveContactAction(currentContactState.value!!)
                    )
                },
                onCancel = {
                    sharedViewModel.submitAction(ContactsListAction.CancelDeletion)
                }
            )
        }
        //This has the potential to be extracted into its own Composable for
        // semantics and better readability
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                    Text("Details", fontSize = 25.sp)
            }

            Divider(thickness = 1.dp)
            FieldDescription(
                label = "Name",
                value = "${currentContactState.value!!.firstName} ${currentContactState.value!!.lastName}",
                icon = Icons.Filled.Person)
            Divider(thickness = 1.dp)
            FieldDescription(
                label = "Phone Number",
                value = currentContactState.value!!.phoneNumber,
                icon = Icons.Filled.Call)
            Divider(thickness = 1.dp)
            FieldDescription(
                label = "Email",
                value = currentContactState.value!!.email,
                icon = Icons.Filled.Email)
            Divider(thickness = 1.dp)

            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly){
                    EditButton(
                        onClick = {
                            sharedViewModel.submitAction(
                                ContactsListAction.EditContactAction(currentContactState.value!!)
                            )
                        }
                    )
                    DeleteButton(
                        onClick = {
                            sharedViewModel.submitAction(
                                ContactsListAction.RequestDeletionConfirmation
                            )
                        }
                    )
            }
        }
    }
}

@Composable
fun EditButton(onClick : () -> Unit){
    Button(
        onClick = onClick
    ){
        Text("Edit")
    }
}

@Composable
fun DeleteButton(onClick : () -> Unit){
    Button(
        onClick = onClick
    ){
        Text("Delete")
    }
}

@Composable
fun FieldDescription(label: String, value : String, icon : ImageVector){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp), horizontalArrangement = Arrangement.Start){
        Icon(
            modifier = Modifier
                .padding(4.dp).align(Alignment.CenterVertically),
            imageVector = icon,
            contentDescription = "$label icon",
            tint = Color.DarkGray
        )
        Column{
        Text("$label: ", fontSize = 14.sp)
        Text(value, fontSize = 18.sp)
        }
    }
}