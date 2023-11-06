package com.example.submissionjatpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissionjatpackcompose.ui.navigation.NavigationItem
import com.example.submissionjatpackcompose.ui.navigation.Screen
import com.example.submissionjatpackcompose.ui.screen.detail.DetailScreen
import com.example.submissionjatpackcompose.ui.screen.home.HomeScreen
import com.example.submissionjatpackcompose.ui.screen.profile.ProfileScreen
import com.example.submissionjatpackcompose.ui.theme.SubmissionJatpackComposeTheme

@Composable
fun PetListApp(
    modifier: Modifier =Modifier,
    navController: NavHostController = rememberNavController(),

    ){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            if (currentRoute!=Screen.DetailPet.route){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ){ innerPadding->
        NavHost(
            navController =navController ,
            startDestination = Screen.Home.route,
            modifier= Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToDetail = {petId ->
                        navController.navigate(Screen.DetailPet.createRoute(petId))
                    }
                )
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(
                route = Screen.DetailPet.route,
                arguments = listOf(navArgument("petId"){type = NavType.LongType}),
            ){
                val id = it.arguments?.getLong("petId")?: -1L
                DetailScreen(
                    petId = id ,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }

        }


    }
}



@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier =Modifier){
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title)
                },
                label = { Text(item.title)},
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState =true
                        launchSingleTop = true
                    }

                }
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    SubmissionJatpackComposeTheme {
        PetListApp()
    }
}