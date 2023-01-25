package `in`.wizion.statussaver.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwi7.saver.model.WAImageModel

class WAImageAdapter(private  val context :Context,private  val arrayList : ArrayList<WAImageModel>) :
    RecyclerView.Adapter<WAImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}
