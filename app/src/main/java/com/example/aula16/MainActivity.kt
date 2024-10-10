package com.example.aula16

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aula16.ui.theme.Aula16Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn



import androidx.compose.runtime.*





import androidx.compose.ui.unit.dp


import retrofit2.await



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaPostsScreen()
        }
    }
}



@Composable
fun ListaPostsScreen() {

    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

    var erro by remember { mutableStateOf<String?>(null) }



    // Fazendo a requisição no LaunchedEffect para que ocorra uma vez

    LaunchedEffect(Unit) {

        try {

            val response = RetrofitInstance.api.getPosts().await()  // Faz a requisição

            posts = response  // Armazena os posts no estado

        } catch (e: Exception) {

            erro = "Erro ao carregar dados"

        }

    }



    if (erro != null) {

        Text(text = erro!!)

    } else {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(posts) { post ->

                PostItem(post)

            }

        }

    }

}




@Composable

fun PostItem(post: Post) {

    Column(modifier = Modifier

        .fillMaxWidth()

        .padding(16.dp)) {

        Text(text = post.title, modifier = Modifier.padding(bottom = 8.dp))

        Text(text = post.body)

    }

}



@Preview(showBackground = true)

@Composable

fun PreviewListaPostsScreen() {

    ListaPostsScreen()

}
