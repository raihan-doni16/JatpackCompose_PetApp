package com.example.submissionjatpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.submissionjatpackcompose.R
import com.example.submissionjatpackcompose.ui.theme.Shapes
import com.example.submissionjatpackcompose.ui.theme.SubmissionJatpackComposeTheme

@Composable
fun PetItem(
    photo: Int,
    name: String,
    kategori: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp
){
    Column(modifier = modifier) {
        Image(
            painter = painterResource(photo) ,
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(Shapes.medium)
        )
        Text(
            text =name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = textSize
            )
        )
        Text(
            text = kategori,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = textSize
            ),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
@Composable
@Preview(showBackground = true)
fun PetItemPreview(){
    SubmissionJatpackComposeTheme {
        PetItem(photo = R.drawable.kucing5, name = "BOBY", kategori ="kucing" )
    }
}