package com.sidratul.sulselexploapp.ui.panel.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sidratul.sulselexploapp.R

@Composable
fun AboutPanel() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.me), contentDescription = null, modifier = Modifier
                .size(250.dp)
                .clip(
                    CircleShape
                ))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Sidratul Muhaira", style = MaterialTheme.typography.h5)
            Text(text = "heramuhaira22012002@gmail.com", color = Color.Gray)
        }
    }
}