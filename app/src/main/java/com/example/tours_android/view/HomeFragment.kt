package com.example.tours_android.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.example.tours_android.R
import com.example.tours_android.model.Movie
import com.example.tours_android.service.EspressoIdlingResource
import com.example.tours_android.service.MovieService
import com.example.tours_android.service.Repository
import com.example.tours_android.viewmodels.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), MovieAdapter.OnItemClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView:RecyclerView?=null
    lateinit var viewModel:MovieViewModel
    var progressBar:ProgressBar?=null
    lateinit var responseCodeTextView: TextView
//    private lateinit var recyclerAdapter:MovieAdapter
   // val movieService = MovieService.getInstance()
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

        //progressBar.can

        var recyclerAdapter=MovieAdapter(this)

//        viewModel= ViewModelProvider(this,
//            MovieViewModelFactory(Repository(movieService)))
//                .get(MovieViewModel::class.java)

        initViewModel()
        val view=inflater.inflate(R.layout.fragment_home, container, false)
       // recyclerView?.adapter= recyclerAdapter

        EspressoIdlingResource.increment()
        responseCodeTextView=view.findViewById(R.id.home_response_code)
        progressBar= view?.findViewById(R.id.home_progress_bar)
        progressBar?.visibility=View.VISIBLE
//               val exampleList=generateDummyList(10)
        recyclerView=view?.findViewById(R.id.recycler_view)

        recyclerView?.adapter=recyclerAdapter
        recyclerView?.layoutManager=LinearLayoutManager(context )
        recyclerView?.setHasFixedSize(true)

        viewModel.responseCode.observe(viewLifecycleOwner, Observer {
            if(it==200){
                progressBar?.visibility=View.GONE
                responseCodeTextView?.visibility=View.GONE
                viewModel.movies.observe(viewLifecycleOwner, Observer {
                    recyclerAdapter.setList(it as ArrayList<Movie>)
                   EspressoIdlingResource.decrement()
                })
            }
            else{
                progressBar?.visibility=View.GONE
                responseCodeTextView?.visibility=View.VISIBLE
                responseCodeTextView.text="Error Code: $it"

            }
        })
        viewModel.fetchMovies()
//        viewModel.movies.observe(viewLifecycleOwner,Observer{it->
//          // print("observing, $it")
//            progressBar?.visibility=View.INVISIBLE
//           recyclerAdapter.setList(it as ArrayList<Movie>)
//        })
//        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
//        })
       // viewModel.getAllMovies()
        return view
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)
            .get(MovieViewModel::class.java)
    }

//    private fun generateDummyList(size:Int) :List<ExampleItem>{
//        val list=ArrayList<ExampleItem>()
//        for(i in 0 until size){
//            val drawable=when(1%3){
//                0->R.drawable.ic_baseline_home_24
//                1->R.drawable.ic_baseline_search_24
//                else->R.drawable.ic_baseline_favorite_24
//            }
//            val item= ExampleItem(drawable,"Item $i","Description $i")
//            list+=item
//        }
//
//        return list
//    }

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

    override fun onItemClick(position: Int) {
        val movie:Movie?=viewModel.movies.value?.get(position)
        if(movie!=null){
            val intent=Intent(context,TourActivity::class.java)
            intent.putExtra("movie",movie)
            startActivity(intent)
        }
        else{
            Toast.makeText(context,"Movie not found",Toast.LENGTH_LONG).show()
        }
//        val intent=Intent(context, TourActivity::class.java)
//        //intent.putParcelableArrayListExtra("list",exampleList[position])
//        intent.putExtra("position",position)
//        startActivity(intent)

        //Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        //notify adapter that value has changed
        //(recyclerView?.adapter as ExampleAdapter).notifyItemChanged(position)

    }
}