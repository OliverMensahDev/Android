package randombgappkotlinandroid.gohool.com.simplelist2_listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var adapter: PersonListAdapter? = null
    private var personList: ArrayList<Person>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personList = ArrayList<Person>()
        layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        adapter = PersonListAdapter(personList!!, this)


        //setup list
        recyclerviewId.layoutManager = layoutManager
        recyclerviewId.adapter = adapter


        //load data
        for (i in 0..9) {
            val mPerson = Person()
            mPerson.name = "Paulo_" + i
            mPerson.age = 12 + i
            personList!!.add(mPerson)
        }
        adapter!!.notifyDataSetChanged()




    }
}
