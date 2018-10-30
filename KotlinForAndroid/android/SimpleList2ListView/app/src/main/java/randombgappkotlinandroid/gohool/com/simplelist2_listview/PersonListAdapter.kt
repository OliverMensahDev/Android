package randombgappkotlinandroid.gohool.com.simplelist2_listview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class PersonListAdapter( private val list: ArrayList<Person>,
                         private val context: Context) : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //we bind views with data
        holder?.bindItem(list[position])


    }


    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): PersonListAdapter.ViewHolder? {
        //Create our view from xml
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


         fun bindItem(items: Person) {

             var name: TextView = itemView.findViewById(R.id.name) as TextView
             var age: TextView = itemView.findViewById(R.id.age) as TextView

             name.text = items.name
             age.text = items.age.toString()

             itemView.setOnClickListener {
                 Toast.makeText(context, name.text, Toast.LENGTH_LONG).show()
             }


         }
    }

}


