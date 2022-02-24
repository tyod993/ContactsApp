package com.trevormontgomery.visacontacts

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.trevormontgomery.visacontacts.ui.state.SharedViewModel
import com.trevormontgomery.visacontacts.ui.theme.VisaContactsTheme

@Composable
fun ContactsApp(){

    val navController = rememberNavController()
    val sharedViewModel : SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    sharedViewModel.navController = navController

        VisaContactsTheme {
            NavHost(navController = navController, startDestination = "main") {
                createAppNavGraph(navController)
            }
        }
}