package com.example.walkly.application

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.walkly.domain.model.Activity
import com.example.walkly.domain.model.GPS
import com.example.walkly.domain.model.mymap.MyMap
import com.example.walkly.domain.model.mymap.Route
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest

/**
 * 現在地の取得やアクティビティの開始などの指示を統括している
 */

class MapApplicationService(private val activity: AppCompatActivity) {
    private lateinit var myMap: MyMap
    private lateinit var route: Route
    private lateinit var mapActivity: Activity // TODO: 名前
    /**
     * マップの準備ができたら現在地を取得し、GoogleMapを保管する
     *
     * @param mMap OnMapReadyCallbackインターフェースのonMapReadyが受け取る引数
     */
    fun startUp(mMap: GoogleMap) {
        val gps = GPS(activity)
        gps.enableCurrentLocation(mMap)
        myMap = MyMap(mMap)
        mapActivity = Activity(mMap)
        route = Route(mMap)
    }

    /**
     * アクティビティの開始
     */
    fun handleActivityButton() {
        // TODO: 現在地を取得する
        // TODO: チェックポイントの位置を取得する
        mapActivity.toggleIsActivity()
        val mMap = myMap.getMyMap()
        if (mapActivity.getIsActivity()) {
            val origin = LatLng(35.1681, 136.8856) // HAL

            val places: MutableList<LatLng> = ArrayList()
            places.add(LatLng(35.1709, 136.8815)) // 名古屋駅
            places.add(LatLng(35.1700, 136.8852)) // ミッドランド
            places.add(LatLng(35.1716, 136.8863)) // ユニモール

            mMap.addMarker(MarkerOptions().position(origin))
            for (j in 0 until places.size) {
                mMap.addMarker(MarkerOptions().position(places[j]))
            }

            route.drawRoute(origin, places)
        } else {
            mMap.clear()
        }
    }

    /**
     * アクティビティを実行中ならマーカーを設置して、保存する(削除やルート用)
     * そうでなければ情報ウインドウを設置する
     *
     * @param point
     */
    fun handlePointClick(point: PointOfInterest) {

        // TODO: アクティビティ中ならtrue
        if (true) {
            myMap.addMarker(point)
        } else {
            Toast.makeText(
                activity,
                """
            ${point.name}
            緯度:${point.latLng.latitude}
            経度:${point.latLng.longitude}
            """,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun handleMarkerClick(marker: Marker) {
    }
}