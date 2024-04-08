package pe.edu.upc.democompose.ui.contactlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.upc.democompose.model.Contact

@Composable
fun ContactList(
    contacts: MutableState<Array<Contact>>,
    newContact: () -> Unit,
    selectContact: (Int) -> Unit
) {


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { newContact() }) {
            Icon(Icons.Filled.Add, "New contact")
        }
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            itemsIndexed(contacts.value) { index, contact ->
                ContactItem(contact, selectContact = {
                    selectContact(index)
                })
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(contact: Contact, selectContact: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp), onClick = {
        selectContact()
    }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = contact.name, modifier = Modifier.fillMaxWidth())
            Text(text = contact.telephone, modifier = Modifier.fillMaxWidth())
        }
    }
}