package me.ebraheem.restaurants.ui.search_city.city_details_dialog

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import me.ebraheem.restaurants.R;
import me.ebraheem.restaurants.data.model.City


const val ARG_CITY = "item_city"


class CityDetailsDialogFragment : BottomSheetDialogFragment(), OnMapReadyCallback {


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
    }

    private var mMap: GoogleMap? = null
    private var mListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mListener = parent as Listener
        } else {
            mListener = context as Listener
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }


    interface Listener {
        fun onAccept(city: City)
    }


    companion object {

        fun newInstance(city: City): CityDetailsDialogFragment =
            CityDetailsDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CITY, city)
                }
            }

    }
}
