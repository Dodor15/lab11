package layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laba11.Books
import com.example.laba11.R

class BookRVAdapter(context: Context?, val data: MutableList<Books>): RecyclerView.Adapter<BookRVAdapter.BookViewHolder?>() {

    private val layoutInflater: LayoutInflater = android.view.LayoutInflater.from(context)

    private var iClickListener: ItemClickListener? = null

    fun setClickListener (itemClickListener: ItemClickListener?){
        iClickListener=itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item_123, parent, false)
        return  BookViewHolder(view)
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = data[position]
        holder.titleTextView.text=item.title
        holder.authorsTextView.text=item.authors.toString()
        holder.IndexTextView.text=item.isindex
        holder.CountTextView.text=item.pageCount.toString()
    }

    override fun getItemCount(): Int =data.size


    inner class BookViewHolder(item: View):RecyclerView.ViewHolder(item),
    View.OnClickListener{
        var titleTextView: TextView = item.findViewById(R.id.tvTitle)
        var authorsTextView: TextView = item.findViewById(R.id.tvAuthors)
        var IndexTextView: TextView = item.findViewById(R.id.tvIndex)
        var CountTextView: TextView = item.findViewById(R.id.tvCount)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?){
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }


}