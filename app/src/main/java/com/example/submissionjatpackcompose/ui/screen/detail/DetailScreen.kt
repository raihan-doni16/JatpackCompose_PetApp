package com.example.submissionjatpackcompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionjatpackcompose.R
import com.example.submissionjatpackcompose.di.Injection
import com.example.submissionjatpackcompose.ui.ViewModelFactory
import com.example.submissionjatpackcompose.ui.common.UiState
import com.example.submissionjatpackcompose.ui.theme.SubmissionJatpackComposeTheme

@Composable
fun DetailScreen(
    petId: Long,
    viewModel: DetailPetViewModel =viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: ()-> Unit,

){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { data ->
        when(data){
            is UiState.Loading ->{
                viewModel.getPetById(petId)
            }
            is UiState.Success -> {
                val petData = data.data
                DetailContent(
                    image =petData.pet.image ,
                    title =petData.pet.title ,
                    kategori =petData.pet.kategori ,
                    descripsi =petData.pet.deskripsi ,
                    onBackClick =navigateBack,
                )

            }
            is UiState.Error ->{

            }
        }
    }

}
@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    kategori: String,
    descripsi: String,
    onBackClick: () -> Unit,
    modifier: Modifier =Modifier
){
    Column(modifier = modifier) {
        Box {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
                    .align(Alignment.TopStart),
                tint = Color.White


            )
        }
        Surface(
            modifier = Modifier.padding(16.dp),

            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall .copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = kategori,
                    style = MaterialTheme.typography.titleMedium .copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = descripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    SubmissionJatpackComposeTheme{
        DetailContent(
            R.drawable.kucing5,
            "persioa",
            "kucing",
            "persia bro",
            onBackClick = {},
        )
    }
}