package Adapter

import Database.GlobalVar
import Interface.CardListener
import Interface.DeleteListener
import Model.Animal
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1_0706012110032.R
import kotlinx.android.synthetic.main.card_animal.view.*

class RVAdapter(val listAnimal: ArrayList<Animal>, val cardListener: CardListener, val deleteListener: DeleteListener):
    RecyclerView.Adapter<RVAdapter.viewHolder>(){
    class viewHolder (itemView: View, val cardListener1: CardListener, val deleteListener1: DeleteListener): RecyclerView.ViewHolder(itemView){

        fun setData(data: Animal){
            itemView.animalName_TextView.text = data.name
            itemView.animalType_TextView.text = data.type
            itemView.animalAge_TextView.text = "Usia : " + data.age.toString() + " Tahun"

            if (data.imageUri.isNotEmpty()) {
                itemView.animal_ImageView.setImageURI(Uri.parse(data.imageUri))
            }

            itemView.editButton.setOnClickListener {
                cardListener1.onCardClick(adapterPosition)
            }

            itemView.deleteButton.setOnClickListener {
                deleteListener1.onCardDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_animal, parent, false)
        return viewHolder(view, cardListener, deleteListener)
    }

    override fun onBindViewHolder(holder: RVAdapter.viewHolder, position: Int) {
        holder.setData(listAnimal[position])
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }

}