package me.kondachok.gobike.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import me.kondachok.gobike.domain.providers.ValueProvider
import me.kondachok.gobike.ui.theme.Dimen

@Composable
fun DefaultWidget(
    modifier: Modifier,
    imageVector: ImageVector = Icons.Outlined.Speed,
    description: String,
    provider: ValueProvider
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(Dimen.dp8)
            )
            .fillMaxSize()
            .padding(horizontal = Dimen.dp8, vertical = Dimen.dp8)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(Dimen.dp24),
                imageVector = imageVector,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.size(Dimen.dp4))
            Text(
                text = description,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        val value by provider.value.collectAsState()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Companion.Center
        ) {
            Text(
                text = value,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 70.sp
                ),
            )
        }
    }
}

