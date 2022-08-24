package com.elewa.composesampleapp.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elewa.composesampleapp.R
import com.elewa.composesampleapp.data.local.entity.ItemEntity


@Composable
fun ItemView(
    title: String,
    subTitle: String,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = subTitle,
                style = TextStyle(fontSize = 12.sp, color = Color.Black)
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }

        IconButton(
            onClick = onDelete
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.Black
            )
        }


    }
}

