package com.example.openinapp

import android.app.Application
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import com.example.openinapp.util.BottomNavGraph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import com.example.openinapp.util.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp {
                MyApp(application = application)
            }
        }

    }
}

@Composable
fun MainApp(content:@Composable ()->Unit){
    content()
}

@Composable
fun MyApp(application: Application) {
    val navController = rememberNavController()
    var selected by remember { mutableStateOf(Icons.Default.Home) }
    Scaffold(
        topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(androidx.compose.ui.graphics.Color(0xFF0e6fff))
                .padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .background(androidx.compose.ui.graphics.Color(0xFF0e6fff))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Text(
                            text = "Dashboard",
                            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White),
                            modifier = Modifier.padding(10.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                selected = Icons.Outlined.Settings
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(androidx.compose.ui.graphics.Color(0xFF2b80ff))
                                .clip(RoundedCornerShape(20.dp))
                        ) {

                            Icon(
                                painter = (painterResource(id = R.drawable.wrench)),
                                contentDescription = "settings",
                                modifier = Modifier.size(24.dp),
                                tint = androidx.compose.ui.graphics.Color.White
                            )
                        }

                    }
                }
            }

        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.background(Color.White)) {
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center

                ) {
                    IconButton(
                        onClick = {
                            selected = Icons.Default.Home
                            navController.navigate(Screens.Links.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .size(48.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.link2), contentDescription = "Home", modifier = Modifier.size(16.dp),
                            tint = if(selected==Icons.Default.Home) Color.Black else Color.LightGray)
                    }
                    Text(text = "Links", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center), modifier = Modifier.padding(5.dp),
                        color = if(selected==Icons.Default.Home) Color.Black else Color.LightGray)
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center

                ){
                    IconButton(
                        onClick = {
                            selected = Icons.Default.Settings
                            navController.navigate(Screens.Campaign.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .size(48.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.magazine2), contentDescription = "Home", modifier = Modifier.size(16.dp),
                            tint = if(selected==Icons.Default.Settings) Color.Black else Color.LightGray)
                    }
                    Text(text = "Campaign", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center), modifier = Modifier.padding(5.dp),
                        color = if(selected==Icons.Default.Settings) Color.Black else Color.LightGray)
                }


                Box(modifier = Modifier
                    .weight(1f)
                    .padding(10.dp), contentAlignment = Alignment.Center) {

                    FloatingActionButton(onClick = {
                        selected = Icons.Default.Add
                        navController.navigate(Screens.Courses.route)
                    }, modifier = Modifier.size(40.dp), containerColor = Color(0xFF0e6fff), contentColor = Color.White) {
                        Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center

                ){
                    IconButton(
                        onClick = {
                            selected = Icons.Default.Build
                            navController.navigate(Screens.Courses.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .size(48.dp)

                    ) {
                        Icon(painter = painterResource(id = R.drawable.fastforward2), contentDescription = "Home", modifier = Modifier.size(16.dp),
                            tint = if(selected==Icons.Default.Build) Color.Black else Color.LightGray)
                    }
                    Text(text = "Courses", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center), modifier = Modifier.padding(5.dp),
                        color = if(selected==Icons.Default.Build) Color.Black else Color.LightGray)
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center

                ){
                    IconButton(
                        onClick = {
                            selected = Icons.Default.Call
                            navController.navigate(Screens.Profile.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .size(48.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.user2), contentDescription = "Home", modifier = Modifier.size(16.dp),
                            tint = if(selected==Icons.Default.Call) Color.Black else Color.LightGray)
                    }
                    Text(text = "Profile", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center), modifier = Modifier.padding(5.dp),
                        color = if(selected==Icons.Default.Call) Color.Black else Color.LightGray)
                }

            }
        },

    ) {
        paddingValues -> BottomNavGraph(navController = navController, paddingValues = paddingValues, application=application)

    }
}
