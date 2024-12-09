package com.example.s1121113a

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.s1121113a.ui.theme.S1121113aTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            S1121113aTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


                    GameScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    // 顏色列表
    val colors = listOf(
        Color(0xff95fe95), // 第一種顏色
        Color(0xfffdca0f), // 第二種顏色
        Color(0xfffea4a4), // 第三種顏色
        Color(0xffa5dfed)  // 第四種顏色
    )

    // 用來控制當前顏色的狀態
    var currentColorIndex by remember { mutableStateOf(0) }

    val gameTime = remember { mutableStateOf(0) }
    val score = remember { mutableStateOf(0) }
    val activity = (LocalContext.current as? Activity)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors[currentColorIndex]) // 設置背景顏色
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0) {
                        // 向右滑動，增加顏色索引
                        currentColorIndex = (currentColorIndex + 1) % colors.size
                    } else if (dragAmount < 0) {
                        // 向左滑動，減少顏色索引
                        currentColorIndex = (currentColorIndex - 1 + colors.size) % colors.size
                    }
                }
            }
    ) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "2024期末上機考(資管二A吳奕伶)",
                fontSize = 18.sp,
                color = Color.Black
            )


            Image(
                painter = painterResource(id = R.drawable.class_a),
                contentDescription = "class_a",
                modifier = Modifier.padding(vertical = 16.dp)
            )


            Text(
                text = "遊戲持續時間: ${gameTime.value} 秒",
                fontSize = 20.sp,
                color = Color.Black
            )


            Text(
                text = "您的成績: ${score.value} 分",
                fontSize = 20.sp,
                color = Color.Black
            )


            Button(
                onClick = {
                    activity?.finish()
                }
            ) {
                Text(text = "結束App")

            }
        }
    }
        }


