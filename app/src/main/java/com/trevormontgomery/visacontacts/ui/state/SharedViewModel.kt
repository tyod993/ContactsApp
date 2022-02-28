package com.trevormontgomery.visacontacts.ui.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.trevormontgomery.visacontacts.data.ContactsListInteractor
import com.trevormontgomery.visacontacts.data.MockDataSource
import com.trevormontgomery.visacontacts.entities.Contact
import com.trevormontgomery.visacontacts.ui.screens.ContactsListAction
import com.trevormontgomery.visacontacts.ui.screens.ContactsListActionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel(), ContactsListActionHandler {

    private val contactsListInteractor = ContactsListInteractor(MockDataSource())
    private val pendingActions = MutableSharedFlow<ContactsListAction>()

    var selectedContact : Contact? = null
    var navController : NavController? = null
    var showDeletionConfirmation = MutableLiveData(false)

    val contactsList : MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>()
    }

    init {
        updateContactsList()
        setupActionHandler()
    }

    //TODO: Too much of the UI depends directly on this function. The depenency should change to
    // the abstract class this ViewModel implements.
    override fun submitAction(action : ContactsListAction){
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun setupActionHandler(){
        viewModelScope.launch {
            pendingActions.collect {
                when(it){
                    is ContactsListAction.AddNewContactAction -> launchAddNewContactWorkflow()
                    is ContactsListAction.EditContactAction -> launchEditContactWorkflow()
                    is ContactsListAction.RemoveContactAction -> launchRemoveContact(it)
                    is ContactsListAction.ViewContactAction -> navigateToViewContact(it)
                    is ContactsListAction.SubmitNewContactAction -> submitNewContact(it)
                    is ContactsListAction.SubmitEditedContact -> submitEditContactRequest(it)
                    is ContactsListAction.CancelDeletion -> cancelDeletion()
                    is ContactsListAction.RequestDeletionConfirmation -> launchDeletionConfirmation()
                }
            }
        }
    }

    private fun launchAddNewContactWorkflow(){
        navController?.navigate("addContactWorkflow")
    }

    private fun submitNewContact(action : ContactsListAction.SubmitNewContactAction){
        contactsListInteractor.addContact(
            firstName = action.firstName,
            lastName = action.lastName,
            email = action.email,
            phoneNumber = action.phoneNumber
            )
        updateContactsList()
        navController?.popBackStack()
    }

    private fun submitEditContactRequest(action : ContactsListAction.SubmitEditedContact){
        contactsListInteractor.editContact(action.contact)
        updateContactsList()
        selectedContact = action.contact
        navController?.popBackStack()
    }

    private fun launchEditContactWorkflow(){
        navController?.navigate("editContactWorkflow")
    }

    private fun launchRemoveContact(action : ContactsListAction.RemoveContactAction){
        contactsListInteractor.deleteContact(action.contact)
        updateContactsList()
        showDeletionConfirmation.value = false
        navController?.navigate("contactsList")
    }

    private fun cancelDeletion(){
        showDeletionConfirmation.value = false
    }

    private fun launchDeletionConfirmation(){
        showDeletionConfirmation.value = true
    }

    private fun navigateToViewContact( action : ContactsListAction.ViewContactAction){
        selectedContact = action.contact
        navController?.navigate("contactDetails")
    }

    private fun updateContactsList(){
        contactsList.value = contactsListInteractor.getAllContacts()
    }




}