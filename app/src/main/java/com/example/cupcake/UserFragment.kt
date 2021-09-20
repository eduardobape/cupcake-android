package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentUserBinding
import com.example.cupcake.model.OrderViewModel

/**
 * [UserFragment] allows the user to save their name for the cupcake order
 */
class UserFragment : Fragment() {

    // Binding object instance corresponding to the fragment_user.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentUserBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentUserBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            userFragment = this@UserFragment
        }
    }

    fun goToNextScreen() {
        sharedViewModel.setUserName(binding?.textInputEditTextOrderUsername?.text.toString())
        findNavController().navigate(R.id.action_userFragment_to_summaryFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_userFragment_to_startFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}