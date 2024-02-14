package com.example.multiselectlazycolumncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multiselectlazycolumncompose.ui.theme.MultiSelectLazyColumnComposeTheme

/*
How you can select multiple items in Jetpack Compose in Android.
https://www.youtube.com/watch?v=pvNcJXprrKM&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=22
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o conteúdo da atividade como um Compose UI
        setContent {
            // Aplica o tema definido em MultiSelectLazyColumnComposeTheme
            MultiSelectLazyColumnComposeTheme {
                // Inicializa uma lista de ListItem com 20 itens, cada um inicializado como não selecionado
                var items by remember {
                    mutableStateOf(
                        (1..20).map {
                            ListItem(
                                title = "Item $it",
                                isSelected = false
                            )
                        }
                    )
                }

                // Define a estrutura da coluna LazyColumn
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Itera sobre os itens da lista
                    items(items.size) { i ->
                        // Define uma linha com texto e ícone (seleção)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                // Adiciona um clique para alternar o estado de seleção do item
                                .clickable {
                                    items = items.mapIndexed { j, item ->
                                        if (i == j) {
                                            item.copy(isSelected = !item.isSelected)
                                        } else item
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Exibe o título do item
                            Text(text = items[i].title)

                            // Se o item estiver selecionado, exibe um ícone de marca de seleção verde
                            if (items[i].isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
