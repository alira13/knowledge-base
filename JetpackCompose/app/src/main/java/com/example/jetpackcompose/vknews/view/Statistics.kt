import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R

@Preview(showBackground = true)
@Composable
fun Statistics() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Row (modifier = Modifier.weight(1f)){
            MenuItem("206", R.drawable.ic_instagram, "")
        }
        Row (modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            MenuItem("205", R.drawable.ic_instagram, "")
            MenuItem("32", R.drawable.ic_instagram, "")
            MenuItem("493", R.drawable.ic_instagram, "")
        }
    }
}

@Composable
fun MenuItem(text: String, imageId: Int, desc: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(imageId),
            contentDescription = desc,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, color = MaterialTheme.colorScheme.onSecondary)
    }
}