package net.sirasu.nearbyshare

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.*
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity() {

    var SERVICE_ID = "atuo.nearby"
    var nickname = "atuo"
    var mRemoteEndpointId:String? = ""
    val activity: MainActivity = this
    val TAG = "myapp"


    /*
    private val mEndpointDiscoveryCallback = object : EndpointDiscoveryCallback() {
        override fun onEndpointFound(endpointId: String, discoveredEndpointInfo: DiscoveredEndpointInfo) {
            // Advertise側を発見した
            Log.d(TAG,"Advertise側を発見した")

            // とりあえず問答無用でコネクション要求してみる
            Nearby.getConnectionsClient(activity)
                .requestConnection(nickname, endpointId, mConnectionLifecycleCallback)
        }

        override fun onEndpointLost(endpointId: String) {
            // 見つけたエンドポイントを見失った
            Log.d(TAG,"見つけたエンドポイントを見失った")
        }
    }

    private val mConnectionLifecycleCallback = object : ConnectionLifecycleCallback() {

        override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {
            // 他の端末からコネクションのリクエストを受け取った時
            Log.d(TAG,"他の端末からコネクションのリクエストを受け取った")

            // とりあえず来る者は拒まず即承認
            Nearby.getConnectionsClient(activity)
                .acceptConnection(endpointId, mPayloadCallback)
        }

        override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {

            // コネクションリクエストの結果を受け取った時
            Log.d(TAG,"コネクションリクエストの結果を受け取った時")

            when (result.status.statusCode) {
                ConnectionsStatusCodes.STATUS_OK -> {
                    // コネクションが確立した。今後通信が可能。
                    Log.d(TAG,"コネクションが確立した。今後通信が可能。")
                    // 通信時にはendpointIdが必要になるので、フィールドに保持する。
                    mRemoteEndpointId = endpointId
                }

                ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED -> {
                    // コネクションが拒否された時。通信はできない。
                    Log.d(TAG,"コネクションが拒否された時。通信はできない。")
                    mRemoteEndpointId = null
                }

                ConnectionsStatusCodes.STATUS_ERROR -> {
                    // エラーでコネクションが確立できない時。通信はできない。
                    Log.d(TAG,"エラーでコネクションが確立できない時。通信はできない。")
                    mRemoteEndpointId = null
                }
            }
        }

        // コネクションが切断された時
        override fun onDisconnected(endpointId: String) {
            Log.d(TAG,"コネクションが切断された")
            mRemoteEndpointId = null
        }

    }

    private val mPayloadCallback = object : PayloadCallback() {
        override fun onPayloadReceived(endpointId: String, payload: Payload) {
            when (payload.type) {
                Payload.Type.BYTES -> {
                    // バイト配列を受け取った時
                    val data = payload.asBytes()!!
                    findViewById<TextView>(R.id.text_date).text = "データもらった！"
                    Log.d(TAG,"バイト配列を受け取った")
                    // 処理
                }
                Payload.Type.FILE -> {
                    // ファイルを受け取った時
                    val file = payload.asFile()!!
                    // 処理
                }
                Payload.Type.STREAM -> {
                    // ストリームを受け取った時
                    val stream = payload.asStream()!!
                    // 処理
                }
            }
        }

        override fun onPayloadTransferUpdate(endpointId: String, update: PayloadTransferUpdate) {
            // 転送状態が更新された時詳細は省略
        }
    }

     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE
        )

        //許可したいpermissionを許可できるように
        if (!EasyPermissions.hasPermissions(this, *permissions)) {
            EasyPermissions.requestPermissions(this, "パーミッションに関する説明", 1, *permissions)
            return
        }



        findViewById<Button>(R.id.advertise).setOnClickListener{
            Log.d(TAG,"advertiseをタップ")
            /*
            Nearby.getConnectionsClient(this)
                .startAdvertising(
                    nickname,
                    SERVICE_ID,
                    mConnectionLifecycleCallback,
                    AdvertisingOptions(Strategy.P2P_STAR)
                )
                .addOnSuccessListener {
                    // Advertise開始した
                    Log.d(TAG,"Advertise開始した")
                }
                .addOnFailureListener {
                    // Advertiseできなかった
                    Log.d(TAG,"Advertiseできなかった")

                }

             */
        }




        findViewById<Button>(R.id.discovery).setOnClickListener{
            Log.d(TAG,"Discoveryをタップ")
            /*
            Nearby.getConnectionsClient(this)
                .startDiscovery(
                    SERVICE_ID,
                    mEndpointDiscoveryCallback,
                    DiscoveryOptions(Strategy.P2P_STAR)
                )
                .addOnSuccessListener {
                    // Discovery開始した
                    Log.d(TAG,"Discovery開始した")
                }
                .addOnFailureListener {
                    // Discovery開始できなかった
                    Log.d(TAG,"Discovery開始できなかった")
                }

             */
        }

        findViewById<Button>(R.id.date_push).setOnClickListener{
            Log.d(TAG,"date_pushをタップ")
            /*
            val data = "Hello world".toByteArray()
            val payload = Payload.fromBytes(data)

            Nearby.getConnectionsClient(activity)
                .sendPayload(mRemoteEndpointId.toString(), payload)
            Log.d(TAG,"データを送った")

             */
        }



    }
}