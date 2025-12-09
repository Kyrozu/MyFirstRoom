package ky.paba.myfirstroom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ky.paba.myfirstroom.database.Note

class adapterNote(private val listNote: MutableList<Note>) :
    RecyclerView.Adapter<adapterNote.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun delData(dtnote: Note)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterNote.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rvitem, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterNote.ListViewHolder, position: Int) {
        var note = listNote[position]

        holder._tvJudul.setText(note.judul)
        holder._tvDeskripsi.setText(note.deskripsi)
        holder._tvTanggal.setText(note.tanggal)

        holder._btnEdit.setOnClickListener {
            val intent = Intent(it.context, TambahData::class.java)
            intent.putExtra("noteId", note.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }
        holder._btnDelete.setOnClickListener {
            onItemClickCallback.delData(note)
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        var _tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        var _tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        var _btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        var _btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    fun isiData(list: MutableList<Note>) {
        this.listNote.clear()
        this.listNote.addAll(list)
        notifyDataSetChanged()
    }
}