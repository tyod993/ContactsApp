package com.trevormontgomery.visacontacts.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trevormontgomery.visacontacts.ui.screens.ContactsListAction
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel

@Composable
fun AddContactButton(sharedViewModel: SharedViewModel) {
    ExtendedFloatingActionButton(
        onClick = {
            sharedViewModel.submitAction(
                ContactsListAction.AddNewContactAction)},
        icon = { Icon(
            modifier = Modifier.size(25.dp),
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "Add Contact Button",
            tint = Color.Black
        )
        } ,
        text = { Text("Add Contact") }
    )
}