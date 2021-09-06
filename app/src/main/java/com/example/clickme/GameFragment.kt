package com.example.clickme

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clickme.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        //set observer
        viewModel.currentTime.observe(viewLifecycleOwner, Observer { time -> binding.gamefragTvTimer.text = DateUtils.formatElapsedTime(time) })
        viewModel.currentButton.observe(viewLifecycleOwner, Observer { button -> moveButton(button) })
        viewModel.score.observe(viewLifecycleOwner, Observer { score -> binding.gamefragTvScore.text = score.toString() })
        viewModel.scoreColor.observe(viewLifecycleOwner, Observer { color -> changeScoreColor(color) })
        viewModel.gameFinish.observe(viewLifecycleOwner, Observer { isFinished -> if(isFinished) gameOver() })

        //set listeners
        binding.gamefragBt1.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt2.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt3.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt4.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt5.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt6.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt7.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragBt8.setOnClickListener { viewModel.gainPoint() }
        binding.gamefragLoGame.setOnClickListener { viewModel.losePoint() }


        return binding.root
    }
    //only show one btn at a time
    private fun moveButton(button: Int){
        binding.gamefragBt1.visibility = View.INVISIBLE
        binding.gamefragBt2.visibility = View.INVISIBLE
        binding.gamefragBt3.visibility = View.INVISIBLE
        binding.gamefragBt4.visibility = View.INVISIBLE
        binding.gamefragBt5.visibility = View.INVISIBLE
        binding.gamefragBt6.visibility = View.INVISIBLE
        binding.gamefragBt7.visibility = View.INVISIBLE
        binding.gamefragBt8.visibility = View.INVISIBLE

        when(button){
            1 -> binding.gamefragBt1.visibility = View.VISIBLE
            2 -> binding.gamefragBt2.visibility = View.VISIBLE
            3 -> binding.gamefragBt3.visibility = View.VISIBLE
            4 -> binding.gamefragBt4.visibility = View.VISIBLE
            5 -> binding.gamefragBt5.visibility = View.VISIBLE
            6 -> binding.gamefragBt6.visibility = View.VISIBLE
            7 -> binding.gamefragBt7.visibility = View.VISIBLE
            else -> binding.gamefragBt8.visibility = View.VISIBLE


        }

    }
    //change score color
    private fun changeScoreColor(color : String){
        if (color == "purple"){
            binding.gamefragTvScore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.purple_700))
        }
        else{
            binding.gamefragTvScore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.my_red))
        }
    }
    //end the game
    private fun gameOver(){
        findNavController().navigate(GameFragmentDirections.actionGameFragment2ToScoreFragment2(viewModel.score.value!!))
    }
}