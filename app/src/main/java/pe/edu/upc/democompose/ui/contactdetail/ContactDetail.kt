package pe.edu.upc.democompose.ui.contactdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.upc.democompose.model.Contact

@Composable
fun ContactDetail(
    navController: NavController,
    saveContact: (Contact) -> Unit,
    contact: Contact? = null
) {
    val name = remember {
        mutableStateOf(contact?.name ?: "")
    }
    val telephone = remember {
        mutableStateOf(contact?.telephone ?: "")
    }
    Scaffold(floatingActionButton = {

        FloatingActionButton(onClick = {

            if (contact == null) {
                val newContact = Contact(name.value, telephone.value)
                saveContact(newContact)
            } else {
                contact.name = name.value
                contact.telephone = telephone.value
                saveContact(contact)
            }
            navController.navigateUp()
        }) {
            Icon(Icons.Filled.Done, "Save")
        }


    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = name.value,
                placeholder = {
                    Text("Name")
                },
                onValueChange = {
                    name.value = it
                })
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                value = telephone.value,
                placeholder = {
                    Text("Telephone")
                },
                onValueChange = {
                    telephone.value = it
                })
        }
    }
}