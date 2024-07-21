package com.example.openinapp.ui.dashboard

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import coil.compose.rememberAsyncImagePainter
import com.example.openinapp.R
import com.example.openinapp.data.model.RecentLink
import com.example.openinapp.data.model.TopLink


@Composable
fun LinksScreen(application: Application) {
    val viewModel: DashboardViewModel = viewModel(factory = DashboardViewModelFactory(application))
    val dashboardResponse by viewModel.dashboardResponse.collectAsState()
    val data = dashboardResponse
    Log.e("LinksScreen", "Data from api: $data")

    val horizontalListData = listOf(
        ItemData(data?.totalClicks.toString(), "Today's clicks", R.drawable.cursor2,color=Color(0xFFded9eb)),
        ItemData("Ahmedabad", "Top Location", R.drawable.pin, color = Color(0xFFe2edff)),
        ItemData("Instagram", "Top Social", R.drawable.globe2, color = Color(0xFFffe9ec)),
    )

    val scrollState = rememberScrollState()
    var selectedOption by remember { mutableStateOf("Top Links") }
    var showAllItems by remember { mutableStateOf(false) }

    // Get the data from the API response
    val topLinksItems:List<TopLink> = data?.data?.topLinks ?: emptyList()
    val recentLinksItems:List<RecentLink> = data?.data?.recentLinks ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e6fff))
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color(0xFFf5f5f5))
            .verticalScroll(scrollState),
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Good morning",
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = "Ajay Manava 👋",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 5.dp) // Adds space between the texts
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(Color.White, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ){
                Column(modifier = Modifier.padding(10.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Overview", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(8.dp))
                    LineChartScreen()
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalListView(horizontalListData)
            Spacer(modifier = Modifier.height(16.dp))
            FullWidthButton(imageRes = R.drawable.priceboost, text = "View Analytics") {
                {/*TODO: Add navigation to Analytics Screen*/}
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                SearchComponent(selectedOption) { newOption ->
                    selectedOption = newOption
                }
                val (items, itemType) = when (selectedOption) {
                    "Top Links" -> topLinksItems to TopLink::class
                    else -> recentLinksItems to RecentLink::class
                }

                // Determine which items to show
                val displayedItems = if (showAllItems) items else items.take(4)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .heightIn(max = 600.dp)
                ) {
                    items(displayedItems) { item ->
                        when (itemType) {
                            TopLink::class -> {
                                val topLinkItem = item as TopLink
                                ItemCardTop(topLinkItem) // Replace with your image resource
                            }
                            RecentLink::class -> {
                                val recentLinkItem = item as RecentLink
                                ItemCardRecent(recentLinkItem) // Replace with your image resource
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            FullWidthButton(imageRes = R.drawable.link2, text = "View All Links") {
                showAllItems = !showAllItems
            }
            Spacer(modifier = Modifier.height(16.dp))
            FullWidthSharingButton(imageRes = R.drawable.vector, text = "Talk with us", color = Color(0xFFe0f1e3)) {
                {/*TODO: Add navigation to Chat Screen*/}
            }
            Spacer(modifier = Modifier.height(8.dp))
            FullWidthSharingButton(imageRes = R.drawable.questionmark, text = "Frequently Asked Questions", color = Color(0xFFe8f1ff)) {
                {/*TODO: Add navigation to FAQ Screen*/}
            }
        }
    }
}


@Composable
fun SearchComponent(selectedOption: String, onOptionSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomRadioButtonGroup(selectedOption, onOptionSelected)
        Button(onClick = { /* Handle search click */ },
            colors = androidx.wear.compose.material.ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.LightGray),
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .border(
                    1.dp,
                    Color.LightGray,
                    RoundedCornerShape(12.dp)
                )
                .padding(vertical = 5.dp)) {
            Icon(Icons.Outlined.Search, contentDescription = "Search", tint = Color.LightGray)
        }
    }
}
@Composable
fun ItemCardTop(item: TopLink) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = item.originalImage),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        EllipsisText(text = item.webLink, maxLength = 15, color = Color.Black)
                        EllipsisText(text = item.createdAt, maxLength = 15, color = Color.Gray)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = item.totalClicks.toString(), style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Clicks", style = TextStyle(fontSize = 14.sp))
                    }
                }
            }

        }
        //Smart Link
        Box(
            modifier = Modifier
                .background(color = Color(0xffe8f1ff))
                .dashedBorder(width = 1.dp, radius = 4.dp, color = Color(0xffa6c7ff))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                EllipsisText(text = item.smartLink, maxLength = 15,color= Color(0xff0e6fff))
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.copy),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                )
            }
        }

    }
}

@Composable
fun ItemCardRecent(item: RecentLink) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = item.originalImage),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        EllipsisText(text = item.webLink, maxLength = 15, color = Color.Black)
                        EllipsisText(text = item.createdAt, maxLength = 15, color = Color.Gray)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = item.totalClicks.toString(), style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Clicks", style = TextStyle(fontSize = 14.sp))
                    }
                }
            }

        }
        //Smart Link
        Box(
            modifier = Modifier
                .background(color = Color(0xffe8f1ff))
                .dashedBorder(width = 1.dp, radius = 4.dp, color = Color(0xffa6c7ff))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                EllipsisText(text = item.smartLink, maxLength = 15,color= Color(0xff0e6fff))
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.copy),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                )
            }
        }


    }
}

@Composable
fun CustomRadioButtonGroup(selectedOption: String, onOptionSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(16.dp)
    ) {
        CustomRadioButton(
            text = "Top Links",
            isSelected = selectedOption == "Top Links",
            onClick = { onOptionSelected("Top Links") }
        )
        Spacer(modifier = Modifier.width(16.dp))
        CustomRadioButton(
            text = "Recent Links",
            isSelected = selectedOption == "Recent Links",
            onClick = { onOptionSelected("Recent Links") }
        )
    }
}

@Composable
fun CustomRadioButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color(0xFF0e6fff) else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun FullWidthButton(imageRes: Int, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = androidx.wear.compose.material.ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Adjust padding as needed
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp) // Reduced vertical padding// Padding for top and bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
        }
    }
}


@Composable
fun FullWidthSharingButton(imageRes:Int, text:String,color:Color, onClick:()->Unit) {
    Button(onClick = onClick,
        colors = androidx.wear.compose.material.ButtonDefaults.buttonColors(backgroundColor = color,contentColor = Color.Black)
        , modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Adjust padding as needed
            .clip(RoundedCornerShape(12.dp)) // Clip the button with rounded corners
            .padding(vertical = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.height(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, style = TextStyle(fontSize = 16.sp,fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun HorizontalListView(items: List<ItemData>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            ItemCard(item,item.color)
        }
    }
}

@Composable
fun ItemCard(item: ItemData, color: Color) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .background(Color.Transparent)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
                .padding(16.dp)
                ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(50)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = item.title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = item.subTitle,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun EllipsisText(text: String, maxLength: Int, modifier: Modifier = Modifier, color: Color=Color(0xFFC0B7E8)) {
    val trimmedText = remember {
        if (text.length > maxLength) {
            text.substring(0, maxLength - 3) + "..."
        } else {
            text
        }
    }

    Text(
        text = trimmedText,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color,
    )
}

fun Modifier.dashedBorder(width: Dp, radius: Dp, color: Color=Color(0xFFa6c7ff)) =
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
                .apply {
                    strokeWidth = width.toPx()
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                }
            it.drawRoundRect(
                width.toPx(),
                width.toPx(),
                size.width - width.toPx(),
                size.height - width.toPx(),
                radius.toPx(),
                radius.toPx(),
                paint
            )
        }
    }

data class ItemData(
    val title: String,
    val subTitle: String,
    val image: Int,
    val color:Color
)

