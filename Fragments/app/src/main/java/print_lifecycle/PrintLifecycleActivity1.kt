package print_lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragments.R


class PrintLifecycleActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_print_lifecycle1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onCreate")

        val fragment = PrintLifecycleFragment1.newInstance("", "")
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()

        val buttonToActivity2 = findViewById<Button>(R.id.btn_toActivity2)
        buttonToActivity2.setOnClickListener {
            val myIntent = Intent(
                this,
                PrintLifecycleActivity2::class.java
            )
            startActivity(myIntent)
        }
    }

    override fun onStart() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d(LIFECYCLE_DEBUG, "PrintLifecycleActivity1 onRestart")
        super.onRestart()
    }


    companion object {
        const val LIFECYCLE_DEBUG = "LIFE"
    }
}