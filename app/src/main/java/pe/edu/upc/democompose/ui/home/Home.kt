package pe.edu.upc.democompose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.democompose.Routes
import pe.edu.upc.democompose.model.Contact
import pe.edu.upc.democompose.ui.contactdetail.ContactDetail
import pe.edu.upc.democompose.ui.contactlist.ContactList

@Composable
fun Home() {
    val navController = rememberNavController()
    val contacts = remember {
        mutableStateOf(emptyArray<Contact>())
    }

    NavHost(navController = navController, startDestination = Routes.ContactList.route) {
        composable(Routes.ContactList.route) {
            ContactList(
                contacts,
                newContact = { navController.navigate(Routes.ContactDetail.routeWithoutArgument) },
                selectContact = { index ->
                    navController.navigate("${Routes.ContactDetail.route}/${index}")
                })

        }
        composable(
            Routes.ContactDetail.routeWithArgument,
            arguments = listOf(navArgument(Routes.ContactDetail.argument) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val index =
                backStackEntry.arguments?.getInt(Routes.ContactDetail.argument) ?: return@composable
            val contact = if (index < 0) {
                Contact("", "")
            } else contacts.value[index]
            ContactDetail(
                navController, saveContact = {

                    if (index < 0) {
                        contacts.value += it
                    } else {
                        contacts.value[index] = it
                    }

                }, contact = contact
            )
        }
    }
}