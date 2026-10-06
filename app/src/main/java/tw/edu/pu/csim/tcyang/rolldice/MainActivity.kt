package tw.edu.pu.csim.tcyang.rolldice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.rolldice.ui.theme.RollDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Dice(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dice(modifier: Modifier = Modifier) {
    // 變數記錄目前的骰子點數 (0 對應 dice0, 1~6 對應 dice1~6)
    var diceNo by remember { mutableIntStateOf(0) }

    // 根據 diceNo 決定要顯示哪張圖片
    val diceImage = when (diceNo) {
        1 -> R.drawable.dice1
        2 -> R.drawable.dice2
        3 -> R.drawable.dice3
        4 -> R.drawable.dice4
        5 -> R.drawable.dice5
        6 -> R.drawable.dice6
        else -> R.drawable.dice0
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "隨機擲骰子\n作者: 陳正倫",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Image(
            painter = painterResource(id = diceImage),
            contentDescription = "骰子",
            modifier = Modifier
                .padding(32.dp)
                .combinedClickable(
                    onClick = {
                        // 單擊：隨機產生 1 到 6 點
                        diceNo = (1..6).random()
                    },
                    onLongClick = {
                        // 長按：變成點數 6
                        diceNo = 6
                    },
                    onDoubleClick = {
                        // 雙擊：回到 0 (dice0)
                        diceNo = 0
                    }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DicePreview() {
    RollDiceTheme {
        Dice()
    }
}