package ky.paba.myfirstroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ky.paba.myfirstroom.database.NoteRoomDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var DB: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = NoteRoomDatabase.getDatabase(this)

        val _rvNotes = findViewById<RecyclerView>(R.id.rvNotes)
        val _fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)

        _fabAdd.setOnClickListener {
            startActivity(Intent(this, TambahData::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val note = DB.funnoteDao().selecAll()
            Log.d("data ROOM", note.toString())
        }
    }
}