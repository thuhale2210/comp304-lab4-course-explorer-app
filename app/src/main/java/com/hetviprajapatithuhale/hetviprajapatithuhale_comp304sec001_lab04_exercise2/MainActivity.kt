package com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.Course
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.Program
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.samplePrograms
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.ui.theme.HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
                ProgramListScreen(samplePrograms)
            }
        }
    }
}

@Composable
fun ProgramListScreen(programs: List<Program>) {
    var selectedProgram by remember { mutableStateOf<Program?>(null) }

    if (selectedProgram == null) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(programs) { program ->
                ProgramItem(program = program, onClick = { selectedProgram = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    } else {
        CourseListScreen(program = selectedProgram, onBack = { selectedProgram = null })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramItem(program: Program, onClick: (Program) -> Unit) {
    Card(
        onClick = { onClick(program) },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = program.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun CourseListScreen(program: Program?, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Courses for ${program?.name}",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(program?.courses ?: emptyList()) { course ->
                CourseItem(course = course)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Back to Programs")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseItem(course: Course) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            if (expanded) {
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
fun PreviewProgramListScreen() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        ProgramListScreen(programs = samplePrograms)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgramItem() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        ProgramItem(program = samplePrograms.first(), onClick = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCourseListScreen() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        CourseListScreen(program = samplePrograms.first(), onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseItem() {
    HetviPrajapatiThuHaLe_COMP304Sec001_Lab04_Exercise2Theme {
        CourseItem(course = samplePrograms.first().courses.first())
    }
}