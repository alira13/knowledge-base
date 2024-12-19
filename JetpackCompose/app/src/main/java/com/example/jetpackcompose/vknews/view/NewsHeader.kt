import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

@Preview()
@Composable
fun NewsHeader() {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth().background(MaterialTheme.colorScheme.secondary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_account),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(64.dp)
            )
            //как пример, но в этом случае лучше padding

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {

                Text(
                    text = "Какая-то группа",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("20:00", fontSize = 16.sp)
            }

            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = ""
            )
        }
    }
}


