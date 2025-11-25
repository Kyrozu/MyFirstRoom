package ky.paba.myfirstroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ky.paba.myfirstroom.database.Note
import ky.paba.myfirstroom.database.NoteRoomDatabase
import ky.paba.myfirstroom.helper.DataHelper.getCurrentDate

class TambahData : AppCompatActivity() {

    val DB: NoteRoomDatabase = NoteRoomDatabase.getDatabase(this)
    var tanggal: String = getCurrentDate()

    lateinit var _etJudul: EditText
    lateinit var _etDeskripsi: EditText
    lateinit var _btnTambah: Button
    lateinit var _btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _etJudul = findViewById(R.id.etJudul)
        _etDeskripsi = findViewById(R.id.etDeskripsi)
        _btnTambah = findViewById(R.id.btnTambah)
        _btnUpdate = findViewById(R.id.btnUpdate)

        _btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.funnoteDao().insert(
                    Note(
                        0,
                        _etJudul.text.toString(),
                        _etDeskripsi.text.toString(),
                        tanggal
                    )
                )
            }
        }



    }


}