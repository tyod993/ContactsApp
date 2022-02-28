package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.ui.composables.AddContactButton
import com.trevormontgomery.visacontacts.ui.composables.ContactCard
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun ContactsListScreen(){

    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val contactsListState by sharedViewModel.contactsList.observeAsState()
    
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            AddContactButton(sharedViewModel = sharedViewModel)
        }
    ) {
        if(contactsListState!!.isNullOrEmpty()) EmptyContactsListMessage()
        LazyColumn{
            itemsIndexed(items = contactsListState!!){ index, item ->
                ContactCard(
                    contact = item,
                    onClick = {
                        sharedViewModel.submitAction(ContactsListAction.ViewContactAction(item))
                    }
                )
            }
        }

    }
}

@Composable
fun EmptyContactsListMessage(){
    Text(
        "There are no contacts to show! Add one using the button in the bottom right corner.",
        modifier = Modifier.padding(10.dp),
        fontSize = 15.sp,
        textAlign = TextAlign.Center
    )
}