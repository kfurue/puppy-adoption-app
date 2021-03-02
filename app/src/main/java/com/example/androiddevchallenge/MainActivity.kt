/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.NavGraph
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.puppyadoption.model.Item

val items = arrayListOf<Item>()
val names = arrayOf("Sink_Leash","Kitty_Urine","Book_Floppy_Disk","Solar_Dislike","System_Solar","Comics_Running","Flowers_Toolbox","Post_office_Water","Android_Fusion","Ice_cream_cone_Dog", "Trees_Whale","Crab_Bird","Light_saber_Cat","Plants_Light_saber","Soda_Solar","Flowers_Clock","Cone_Puppy","Websites_Hnads","Printer_Book")
val owners = arrayOf("Plants_Solar","Poop_Boat","Toilet_Trees","Leash_Video_games","Cat_Puppy","Running_Bird","YouTube_Whale","Plants_Hnads","Leash_Leash","Post_office_Hnads", "Cat_Fusion","Sink_Allergies","Elevator_Ice_cream_cone","Light_saber_Book","Settings_Printer","Boat_Soda","Breakfast_Shoes","Shoes_Bird","Puppy_Toolbox")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in names.indices) {
            val fileName = "puppyadoptionimage_${i+1}"
            items.add(
                Item(
                    id = i,
                    title = names[i],
                    subtitle = owners[i],
                    imageId = resources.getIdentifier(fileName, "drawable", packageName)
                )
            )
        }
        setContent {
            NavGraph()
        }
    }
}

@Composable
fun NewsStory(detailId: Int) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(items[detailId].imageId),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))

        Text(
            items[detailId].title,
            style = MaterialTheme.typography.h6
        )
        items[detailId].subtitle?.let {
            Text(
                it,
                style = MaterialTheme.typography.body2
            )
        }
        Text(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse fringilla dictum odio, sed tristique metus ultricies vitae. Suspendisse eleifend tincidunt felis id imperdiet. Vivamus rhoncus in augue vitae vulputate. Nam lacinia rutrum tellus, sit amet convallis sem viverra vel. Curabitur varius facilisis ornare. Curabitur auctor turpis a turpis eleifend aliquam. Praesent aliquam porta tortor, vitae laoreet metus congue vel. Fusce cursus, mauris consequat ultricies imperdiet, orci dolor euismod felis, sit amet porttitor sem dolor a lorem. Nullam volutpat vel metus in luctus. Duis vitae tincidunt nulla, sit amet commodo urna. Vivamus molestie mauris eu mi varius porttitor. Nam luctus sapien id pulvinar pellentesque. Aliquam pharetra mauris ac volutpat congue.",
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun Message(index: Int, onClick: () -> Unit) {
    Row(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(16.dp)
    ) {
        Image(
            painter = painterResource(items[index].imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(60.dp, 60.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = items[index].title,
                style = MaterialTheme.typography.subtitle1
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                val textStyle = MaterialTheme.typography.body2
                items[index].subtitle?.let {
                    Text(
                        text = it,
                        style = textStyle
                    )
                }
                Text(
                    text = " - ${index%4} yrs",
                    style = textStyle
                )
            }
        }
    }
}


@Composable
fun ItemsList(messages: Array<String>, onClick: (Int) -> Unit) {
    LazyColumn {
        itemsIndexed(messages) { index, _ ->
            Message(index, onClick = { onClick(index) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme() {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            NewsStory(1)
            ItemsList(Array(19) { "n = $it" }, {})
        }
    }
}
