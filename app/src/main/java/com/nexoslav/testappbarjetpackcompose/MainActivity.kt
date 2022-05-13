package com.nexoslav.testappbarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nexoslav.testappbarjetpackcompose.ui.theme.PageTitleStyle
import com.nexoslav.testappbarjetpackcompose.ui.theme.TestAppBarJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppBarJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreenComposable()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOfColumnTestComposable() {
    MainScreenComposable()
}

@Composable
fun MainScreenComposable() {

    AliunidScreen(
        modifier = Modifier.background(
            Color(0xff293330)
        ),
        appBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(painterResource(id = R.drawable.ic_arrow_back), "backIcon")
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    text = "My energy",
                    style = PageTitleStyle
                )
            }
        }
    ) {
//        ScreenContent(modifier)
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .padding(top = 40.dp, bottom = 30.dp)
        ) {
            List(50) {
                "Some string $it"
            }.map {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 20.dp,
                            bottom = 20.dp
                        )
                )
            }
        }
    }
}

@Composable
fun AliunidScreen(
    modifier: Modifier = Modifier,
    appBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (appbarRef, gradientBox, contentRef) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(appbarRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .background(
                    color = Color(0xff293330)
                )
                .zIndex(1f)
        ) {
            appBar()
        }
        Box(
            modifier = Modifier
                .constrainAs(gradientBox) {
                    top.linkTo(appbarRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(30.dp)
                }
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xff293330),
                            Color(0xee293330),
                            Color(0xdd293330),
                            Color(0xcc293330),
                            Color(0xbb293330),
                            Color(0xaa293330),
                            Color(0x99293330),
                            Color(0x88293330),
                            Color(0x77293330),
                            Color(0x55293330),
                            Color(0x44293330),
                            Color(0x33293330),
                            Color(0x22293330),
                            Color(0x11293330)
                        )
                    )
                )
                .zIndex(1f)
        )
        Box(
            modifier = Modifier.constrainAs(contentRef) {
                top.linkTo(gradientBox.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }
        ){
            content()
        }
    }
}
