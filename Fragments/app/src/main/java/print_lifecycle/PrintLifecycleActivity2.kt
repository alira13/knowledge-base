package print_lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragments.R

class PrintLifecycleActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_print_lifecycle2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity2 onRestart")
        super.onRestart()
    }


    companion object {
        const val LIFECYCLE_DEBUG = "LIFE"
    }
}