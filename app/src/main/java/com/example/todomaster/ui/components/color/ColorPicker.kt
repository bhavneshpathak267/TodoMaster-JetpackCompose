package com.example.todomaster.ui.components.color

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    selectedColor: Long,
    onColorSelected: (Long) -> Unit
) {

    val colors = listOf(

        0xFF6750A4,
        0xFFE91E63,
        0xFFF44336,
        0xFFFF9800,
        0xFFFFEB3B,
        0xFF4CAF50,
        0xFF009688,
        0xFF2196F3,
        0xFF3F51B5

    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        colors.forEach { color ->

            Box(

                modifier = Modifier

                    .size(36.dp)

                    .clip(CircleShape)

                    .background(Color(color))

                    .border(

                        width =
                            if (selectedColor == color)
                                3.dp
                            else
                                1.dp,

                        color =
                            if (selectedColor == color)
                                MaterialTheme.colorScheme.onBackground
                            else
                                Color.Transparent,

                        shape = CircleShape

                    )

                    .clickable {

                        onColorSelected(color)

                    }

            )

        }

    }

}