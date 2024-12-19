import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

@Preview(showBackground = true)
@Composable
fun NewsContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.ic_android),
            contentDescription = ""
        )
    }
}



