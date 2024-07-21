package com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.Course
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.Program
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.programList
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.ui.theme.HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.ui.theme.primaryGreen
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.ui.theme.primaryWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onEnterClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                contentDescription = "Welcome Image"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Hello student!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight(1000),
                color = primaryGreen,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Let's explore the course list in semester 4.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = onEnterClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryGreen,
                    contentColor = primaryWhite
                ),
                modifier = Modifier.width(100.dp).height(50.dp)
            ) {
                Text(
                    text = "Enter"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        WelcomeScreen(onEnterClicked = {})
    }
}

@Composable
fun ProgramListScreen(programs: List<Program>, onProgramSelected: (Program) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Find Your Program",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight(1000),
            modifier = Modifier.padding(top = 46.dp, bottom = 32.dp)
        )
        LazyColumn {
            items(programs) { program ->
                ProgramItem(program = program, onClick = { onProgramSelected(it) })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramItem(program: Program, onClick: (Program) -> Unit) {
    Card(
        onClick = { onClick(program) },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = primaryGreen,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = primaryWhite)
    ) {
        Text(
            text = program.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            color = primaryGreen
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProgramListScreen() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        ProgramListScreen(programs = programList, onProgramSelected = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgramItem() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        ProgramItem(program = programList.first(), onClick = {})
    }
}

@Composable
fun CourseListScreen(program: Program?, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "List of Courses",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight(1000),
            modifier = Modifier.padding(top = 46.dp, bottom = 8.dp)
        )
        Text(
            text = "${program?.name}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        LazyColumn {
            items(program?.courses ?: emptyList()) { course ->
                CourseItem(course = course)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryGreen,
                contentColor = primaryWhite
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp).height(50.dp)
        ) {
            Text(text = "Back to Programs", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseItem(course: Course) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = primaryGreen,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = primaryWhite)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(500),
                color = primaryGreen
            )
            if (expanded) {
                Text(
                    text = course.code,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight(500),
                    color = primaryGreen
                )
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCourseListScreen() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        CourseListScreen(program = programList.first(), onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseItem() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        CourseItem(course = programList.first().courses.first())
    }
}