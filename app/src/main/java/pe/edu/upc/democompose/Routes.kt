package pe.edu.upc.democompose

sealed class Routes (val route: String) {
    data object ContactList: Routes(route = "ContactList")
    data object ContactDetail: Routes(route = "ContactDetail") {
        const val routeWithArgument = "ContactDetail/{index}"
        const val argument = "index"
        const val routeWithoutArgument = "ContactDetail/-1"
    }
}