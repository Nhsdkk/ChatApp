package com.eclipsel.chatapp.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import java.time.LocalDate
import java.time.Month

val months = Month.entries.map { it.toString() }
const val ITEMS_TO_DISPLAY = 3

@Composable
fun CustomDatePicker(
    pickedDate: LocalDate,
    onPickDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val date = pickedDate.dayOfMonth
    val month = pickedDate.month
    val year = pickedDate.year
    
    val daysInMonth = if (pickedDate.isLeapYear) month.maxLength() else month.minLength()
    
    val currentYear = LocalDate.now().year 
    val minYear = currentYear - 50
    val maxYear = currentYear + 50
    
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        ContinuousScrollPicker(
            items = (1..daysInMonth).toList(),
            onPick = { dateIdx -> 
                onPickDate(pickedDate.withDayOfMonth(dateIdx + 1)) 
            },
            currentValueIdx = date - 1, 
            modifier = Modifier.weight(1f),
        )
        ContinuousScrollPicker(
            items = months,
            onPick = { monthIdx ->
                onPickDate(pickedDate.withMonth(monthIdx + 1)) 
            },
            currentValueIdx = month.value, 
            modifier = Modifier.weight(1f),
        )
        ContinuousScrollPicker(
            items = (minYear..maxYear).toList(),
            onPick = { yearIdx ->
                val newYear = minYear + yearIdx + 1
                onPickDate(pickedDate.withYear(newYear))
            },
            currentValueIdx = year - minYear, 
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
fun CustomDatePickerPreview() {
    ChatAppTheme {
        CustomDatePicker(
            pickedDate = LocalDate.now(),
            onPickDate = {}
        )
    }
}

@Composable
fun <T> ContinuousScrollPicker(
    items: List<T>,
    onPick: (Int) -> Unit,
    currentValueIdx: Int,
    modifier: Modifier = Modifier
) {
    val currentOnPick = rememberUpdatedState(onPick)
    val itemCount = items.count() + 2
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = currentValueIdx - 1)
    val centerIdx = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + 1
        }
    }
    var maxItemHeight by remember { mutableIntStateOf(value = 0) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { 
                if (it) return@collect
                val idx = centerIdx.value - 1
                currentOnPick.value(idx)
            }
    }
    
    LazyColumn(
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(pixelsToDp(maxItemHeight * ITEMS_TO_DISPLAY)),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    ) {
        items(count = itemCount, key = { it }) { newIdx ->
            val idx = newIdx - 1
            
            val zeroIndex = 0
            val maxIndex = items.count() + 1
            
            when (newIdx) {
                zeroIndex -> Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0f)
                )
                maxIndex -> Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0f)
                )
                centerIdx.value -> Text(
                    text = "${items[idx]}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.onSizeChanged {
                        if (maxItemHeight < it.height)
                            maxItemHeight = it.height
                    }
                )
                centerIdx.value - ((ITEMS_TO_DISPLAY - 1) / 2), centerIdx.value + ((ITEMS_TO_DISPLAY - 1) / 2) -> Text(
                    text = "${items[idx]}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.onSizeChanged {
                        if (maxItemHeight < it.height)
                            maxItemHeight = it.height
                    }
                )
                else -> Text(
                    text = "${items[idx]}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0f),
                    modifier = Modifier.onSizeChanged { 
                        if (maxItemHeight < it.height) 
                            maxItemHeight = it.height 
                    }
                )
            }
        }
    }
}

@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() } 