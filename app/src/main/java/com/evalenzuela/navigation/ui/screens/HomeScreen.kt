package com.evalenzuela.navigation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evalenzuela.navigation.data.model.Item
import com.evalenzuela.navigation.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, onItemClick: (Int) -> Unit) {
    val items = viewModel.items.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Recetas") })
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(items.value) { item ->
                ItemRow(item = item, onClick = { onItemClick(item.id) })
                Divider()
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(item.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(item.description, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
    }
}