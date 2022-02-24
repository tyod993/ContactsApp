package com.trevormontgomery.visacontacts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.trevormontgomery.visacontacts.ui.screens.*

fun NavGraphBuilder.createAppNavGraph(navController: NavController){
    navigation(startDestination = "contactsList", route = "main"){
        composable("contactsList"){ ContactsListScreen() }
        composable("editContactWorkflow"){ EditContactWorkflow() }
        composable("contactDetails"){ ContactDetails() }
        composable("addContactWorkflow"){ AddContactWorkflow() }
    }
}