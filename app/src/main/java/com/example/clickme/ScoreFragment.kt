package com.example.clickme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clickme.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding : FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_score, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        //set btn click listeners
        binding.scorefragBtGamble.setOnClickListener { viewModel.gamble() }
        binding.scorefragBtPlay.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragment2ToGameFragment2()) }
        binding.scorefragBtTitle.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragment2ToTitleFragment2()) }

        //set data binding to bind data directly to views
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}