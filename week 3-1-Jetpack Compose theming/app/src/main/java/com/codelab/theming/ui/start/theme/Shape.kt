package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val JetnewsShape = Shapes(
    small = CutCornerShape(
        topStart = 8.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp,
    ),
    medium = CutCornerShape(
        topStart = 24.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp,
    ),
    large = RoundedCornerShape(8.dp),
)