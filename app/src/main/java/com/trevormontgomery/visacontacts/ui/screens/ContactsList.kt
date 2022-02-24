package com.trevormontgomery.visacontacts.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trevormontgomery.visacontacts.ui.composables.AddContactButton
import com.trevormontgomery.visacontacts.ui.composables.ContactCard
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun ContactsListScreen(){

    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val contactsListState by sharedViewModel.contactsList.observeAsState()

    //TODO: Make sure that this is scrollable
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            AddContactButton(sharedViewModel = sharedViewModel)
        }
    ) {
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