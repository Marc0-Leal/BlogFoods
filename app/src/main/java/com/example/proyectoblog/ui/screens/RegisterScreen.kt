package com.example.proyectoblog.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoblog.ui.components.TextFieldModificado
import com.example.proyectoblog.viewmodel.AddViewModel

@Composable
fun RegisterScreen(vm: AddViewModel = viewModel()){

    Scaffold{ padding -> Column(modifier = Modifier.padding(padding))}
        TextFieldModificado(vm.formState.value.name,
            {vm.onChangeName(it)},
            vm.formState.value.nameError, "Prueba")
}