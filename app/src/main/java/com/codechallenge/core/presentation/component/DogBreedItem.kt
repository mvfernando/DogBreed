package com.codechallenge.core.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.codechallenge.R
import com.codechallenge.core.domain.model.DogBreed

@Composable
fun DogBreedItem(
    dogBreed: DogBreed,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.border(
            width = 3.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(10)
        )
    ) {
        AnimatedVisibility(visible = true) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .testTag(stringResource(R.string.dog_image))
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                SubcomposeAsyncImage(
                    model = dogBreed.imageUrl,
                    contentDescription = stringResource(R.string.dog_image)
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Success -> {
                            SubcomposeAsyncImageContent(
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStartPercent = 10,
                                            topEndPercent = 10
                                        )
                                    )
                            )
                        }

                        is AsyncImagePainter.State.Error -> {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = stringResource(R.string.error)
                            )
                        }

                        else -> CircularProgressIndicator()
                    }
                }
            }
        }
        Text(
            text = dogBreed.name,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}
