package com.evalenzuela.navigation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.evalenzuela.navigation.navigation.Routes
import com.evalenzuela.navigation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    // PASO 1: Añadir NavController y AuthViewModel como parámetros
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Perfil") }) }
    ) { padding ->
        // Se cambió a una Columna para organizar mejor el texto y el botón
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra los elementos verticalmente
        ) {
            Text("Pantalla de perfil")

            Spacer(modifier = Modifier.height(16.dp)) // Un poco de espacio

            // PASO 2: Añadir el botón que solicitaste
            Button(onClick = {
                authViewModel.logout()
                navController.navigate(Routes.LOGIN) {
                    // Limpia la pila de navegación hasta HOME para que el usuario no pueda "volver atrás" a las pantallas internas
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }) {
                Text("Cerrar sesión")
            }
        }
    }
}
