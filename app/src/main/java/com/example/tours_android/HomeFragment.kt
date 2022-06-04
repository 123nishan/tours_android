package com.example.tours_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tours_android.viewmodels.ExampleItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
               val exampleList=generateDummyList(10)
                recyclerView=view?.findViewById(R.id.recycler_view)

        recyclerView?.layoutManager=LinearLayoutManager(context )
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter=ExampleAdapter(exampleList)
        return view
    }


    private fun generateDummyList(size:Int) :List<ExampleItem>{
        val list=ArrayList<ExampleItem>()
        for(i in 0 until size){
            val drawable=when(1%3){
                0->R.drawable.ic_baseline_home_24
                1->R.drawable.ic_baseline_search_24
                else->R.drawable.ic_baseline_favorite_24
            }
            val item= ExampleItem(drawable,"Item $i","Description $i")
            list+=item
        }

        return list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}